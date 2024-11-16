package vn.HiepKa.controllers.AuthServlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HiepKa.models.UserModel;
import vn.HiepKa.services.IUserService;
import vn.HiepKa.services.impl.UserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;

@WebServlet("/authentication/auth/google")
public class GoogleAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CLIENT_ID = "1013728767709-o6q514dv1kk6h8eh3asqsalkdi3tbfud.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "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";
	private static final String REDIRECT_URI = "https://localhost:8443/Project_Sach/authentication/auth/google";

	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code != null) {
			// Trao đổi mã xác thực để lấy token từ Google
			String tokenEndpoint = "https://oauth2.googleapis.com/token";
			URI uri = URI.create(tokenEndpoint);
			URL url = uri.toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);

			// Tham số gửi đi để trao đổi mã
			String params = "code=" + code + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET
					+ "&redirect_uri=" + REDIRECT_URI + "&grant_type=authorization_code";

			try (OutputStream os = conn.getOutputStream()) {
				os.write(params.getBytes());
			}

			// Đọc phản hồi từ Google chứa access token và ID token
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Lấy phản hồi JSON
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				// Xử lý token nhận được
				String jsonResponse = content.toString();
				// Parse JSON to extract ID token
				String idTokenString = parseIdTokenFromResponse(jsonResponse);

				try {
					// Xác thực và giải mã ID Token để lấy thông tin người dùng
					GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
							GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
							.setAudience(Collections.singletonList(CLIENT_ID)).build();

					GoogleIdToken idToken = verifier.verify(idTokenString);
					if (idToken != null) {
						GoogleIdToken.Payload payload = idToken.getPayload();

						// Lấy thông tin người dùng từ payload
						String email = payload.getEmail();
						String username = (String) payload.get("name");
						String pictureUrl = (String) payload.get("picture");

						// Kiểm tra người dùng
						UserModel user = userService.FindByEmail(email);
						if (user != null) {
							// Người dùng đã tồn tại
							if (user.getPassword() == null) {
								// Người dùng chưa có mật khẩu
								// Chuyển hướng đến trang reset-password.jsp
								req.getSession().setAttribute("email", email); // Đưa email vào session
								// Sau khi xác định người dùng chưa có mật khẩu, chuyển hướng với action=create
								resp.sendRedirect(
										req.getContextPath() + "/authentication/reset-password?action=create");

								return;
							} else {
								// Nếu người dùng đã có tài khoản và mật khẩu, tiến hành đăng nhập
								req.getSession().setAttribute("username", username);
								req.getSession().setAttribute("email", email);
								req.getSession().setAttribute("pictureUrl", pictureUrl);

								userService.login(email, user.getPassword());

								HttpSession session = req.getSession(true);
								session.setAttribute("account", user);

								resp.sendRedirect(req.getContextPath() + "/waiting");
							}

						} else { // Người dùng chưa tồn tại
							// Đăng ký tài khoản mới cho người dùng mà chưa tạo mật khẩu
							userService.register(email, username, null);
							req.getSession().setAttribute("email", email);
							// Sau khi xác định người dùng chưa có mật khẩu, chuyển hướng với action=create
							resp.sendRedirect(req.getContextPath() + "/authentication/reset-password?action=create");

							return;
						}

					} else {
						resp.getWriter().write("Invalid ID token.");
					}
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
					resp.getWriter().write("Security exception: " + e.getMessage());
				}

			} else {
				resp.getWriter().write("Error exchanging code for token.");
			}
		} else {
			resp.getWriter().write("Authorization code not found.");
		}
	}

	private String parseIdTokenFromResponse(String jsonResponse) {
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		return jsonObject.get("id_token").getAsString(); // Lấy id_token từ JSON
	}
}