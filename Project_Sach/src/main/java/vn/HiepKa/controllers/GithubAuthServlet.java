package vn.HiepKa.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/authentication/auth/github")
public class GithubAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CLIENT_ID = "Ov23liRlJeDjCQWzTSHR"; // Thay bằng Client ID của bạn
	private static final String CLIENT_SECRET = "b0af81692e070f78880fd35ba15355aec802a27c"; // Thay bằng Client Secret
																							// của bạn
	private static final String REDIRECT_URI = "https://localhost:8443/Project_Sach/authentication/auth/github";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code != null) {
			// Trao đổi mã xác thực để lấy token
			String tokenEndpoint = "https://github.com/login/oauth/access_token";
			URI uri = URI.create(tokenEndpoint);
			URL url = uri.toURL();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json"); // Yêu cầu GitHub trả về JSON
			conn.setDoOutput(true);

			// Tham số gửi đi để trao đổi mã
			String params = "code=" + code + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET
					+ "&redirect_uri=" + REDIRECT_URI;

			try (OutputStream os = conn.getOutputStream()) {
				os.write(params.getBytes());
			}

			// Đọc phản hồi từ GitHub chứa access token
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
				String accessToken = parseAccessTokenFromResponse(jsonResponse);

				// Tiếp tục lấy thông tin người dùng từ GitHub
				getUserInfo(accessToken, req, resp);

			} else {
				resp.getWriter().write("Error exchanging code for token.");
			}
		} else {
			resp.getWriter().write("Authorization code not found.");
		}
	}

	// Phương thức để lấy access_token từ phản hồi JSON
	private String parseAccessTokenFromResponse(String jsonResponse) {
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		return jsonObject.get("access_token").getAsString(); // Lấy access_token từ JSON
	}

	// Phương thức để lấy thông tin người dùng từ GitHub bằng access_token
	private void getUserInfo(String accessToken, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userInfoUrl = "https://api.github.com/user";
		URI uri = URI.create(userInfoUrl);
		URL url = uri.toURL();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		// Parse JSON để lấy thông tin người dùng
		JsonObject userInfo = JsonParser.parseString(content.toString()).getAsJsonObject();
		String name = userInfo.has("login") && !userInfo.get("login").isJsonNull() ? userInfo.get("login").getAsString()
				: "No login provided";

		// Đặt giá trị mặc định cho email
		String email = "No email provided";

		// Kiểm tra xem email có trong phản hồi thông tin người dùng hay không
		if (userInfo.has("email") && !userInfo.get("email").isJsonNull()) {
			email = userInfo.get("email").getAsString();
		} else {
			// Nếu không có email, chúng ta sẽ gọi API để lấy danh sách email
			email = getUserPrimaryEmail(accessToken);
		}

		// Lưu thông tin người dùng vào session
		req.getSession().setAttribute("userName", name);
		req.getSession().setAttribute("email", email);

		// Debug: Hiển thị thông tin người dùng
		System.out.print("Thông tin người dùng từ GitHub:\n");
		System.out.print("Tên đăng nhập: " + name + "\n");
		System.out.print("Email: " + email + "\n");

		// Chuyển hướng người dùng đến trang chính sau khi đăng nhập thành công
		resp.sendRedirect(req.getContextPath() + "/views/user/home.jsp");
	}

	private String getUserPrimaryEmail(String accessToken) throws IOException {
		// Lấy danh sách email từ GitHub
		String emailUrl = "https://api.github.com/user/emails";
		URI uri = URI.create(emailUrl);
		URL url = uri.toURL();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		// Parse JSON để lấy danh sách email
		JsonArray emails = JsonParser.parseString(content.toString()).getAsJsonArray();
		String primaryEmail = "No email found";
		for (JsonElement emailElement : emails) {
			JsonObject emailObj = emailElement.getAsJsonObject();
			// Lấy email chính (primary) và xác thực (verified)
			if (emailObj.has("primary") && emailObj.get("primary").getAsBoolean()) {
				primaryEmail = emailObj.get("email").getAsString();
				break;
			}
		}

		return primaryEmail;
	}

}
