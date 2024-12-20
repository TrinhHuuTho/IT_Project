package vn.HiepKa.controllers.AuthServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HiepKa.models.UserModel;
import vn.HiepKa.services.IUserService;
import vn.HiepKa.services.impl.UserService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@WebServlet("/authentication/auth/facebook")
public class FacebookAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CLIENT_ID = "859828519474363"; // Thay thế bằng App ID của bạn
	private static final String CLIENT_SECRET = "2b1e1a0de9311df694437a4c5ea7efb7"; // Thay thế bằng App Secret của bạn
	private static final String REDIRECT_URI = "https://localhost:8443/Project_Sach/authentication/auth/facebook";

	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code != null) {
			// Trao đổi mã xác thực để lấy token
			String tokenEndpoint = "https://graph.facebook.com/v7.0/oauth/access_token";
			URI uri = URI.create(tokenEndpoint);
			URL url = uri.toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);

			// Tham số gửi đi để trao đổi mã
			String params = "client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&client_secret="
					+ CLIENT_SECRET + "&code=" + code;

			try (OutputStream os = conn.getOutputStream()) {
				os.write(params.getBytes());
			}

			// Đọc phản hồi từ Facebook chứa access token
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();

				// Xử lý token nhận được
				String jsonResponse = content.toString();
				String accessToken = parseAccessTokenFromResponse(jsonResponse);

				// Tiếp tục lấy thông tin người dùng
				getUserInfo(accessToken, req, resp);

			} else {
				resp.getWriter().write("Error exchanging code for token.");
			}
		} else {
			resp.getWriter().write("Authorization code not found.");
		}
	}

	private String parseAccessTokenFromResponse(String jsonResponse) {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		return jsonObject.getString("access_token");
	}

	private void getUserInfo(String accessToken, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userInfoUrl = "https://graph.facebook.com/me?fields=id,name,email&access_token=" + accessToken;

		URI uri = URI.create(userInfoUrl);
		URL url = uri.toURL();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		// Parse JSON response to get user info
		JSONObject userInfo = new JSONObject(content.toString());
		String name = userInfo.getString("name");
		String email = userInfo.getString("email");

		// Kiểm tra người dùng
		UserModel user = userService.FindByEmail(email);
		if (user != null) {
			// Người dùng đã tồn tại
			if (user.getPassword() == null) {
				// Người dùng chưa có mật khẩu
				// Chuyển hướng đến trang reset-password.jsp
				req.getSession().setAttribute("email", email); // Đưa email vào session
				// Sau khi xác định người dùng chưa có mật khẩu, chuyển hướng với action=create
				resp.sendRedirect(req.getContextPath() + "/authentication/reset-password?action=create");

				return;
			} else {
				// Nếu người dùng đã có tài khoản và mật khẩu, tiến hành đăng nhập
				req.getSession().setAttribute("username", name);
				req.getSession().setAttribute("email", email);

				userService.login(email, user.getPassword());

				HttpSession session = req.getSession(true);
				session.setAttribute("account", user);

				resp.sendRedirect(req.getContextPath() + "/waiting");
			}

		} else {
			// Người dùng chưa tồn tại
			// Đăng ký tài khoản mới cho người dùng mà chưa tạo mật khẩu
			userService.register(email, name, null);
			req.getSession().setAttribute("email", email);
			// Sau khi xác định người dùng chưa có mật khẩu, chuyển hướng với action=create
			resp.sendRedirect(req.getContextPath() + "/authentication/reset-password?action=create");

			return;
		}

		// Debug: Hiển thị thông tin người dùng
		System.out.print("Thông tin người dùng từ Facebook:\n");
		System.out.print("Tên đăng nhập: " + name + "\n");
		System.out.print("Email: " + email + "\n");

	}
}
