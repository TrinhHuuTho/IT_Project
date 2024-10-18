package vn.HiepKa.controllers;

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
    private static final String REDIRECT_URI = "http://localhost:8081/Project_Sach/authentication/auth/google";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        if (code != null) {
            // Trao đổi mã xác thực để lấy token
            String tokenEndpoint = "https://oauth2.googleapis.com/token";
            URI uri = URI.create(tokenEndpoint);
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // Tham số gửi đi để trao đổi mã
            String params = "code=" + code
                    + "&client_id=" + CLIENT_ID
                    + "&client_secret=" + CLIENT_SECRET
                    + "&redirect_uri=" + REDIRECT_URI
                    + "&grant_type=authorization_code";

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
                            GoogleNetHttpTransport.newTrustedTransport(),
                            GsonFactory.getDefaultInstance())
                            .setAudience(Collections.singletonList(CLIENT_ID))
                            .build();

                    GoogleIdToken idToken = verifier.verify(idTokenString);
                    if (idToken != null) {
                        GoogleIdToken.Payload payload = idToken.getPayload();

                        // Lấy thông tin người dùng từ payload
                        String userId = payload.getSubject(); // ID duy nhất của người dùng Google
                        String email = payload.getEmail();
                        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                        String name = (String) payload.get("name");
                        String pasString = (String) payload.get("password");
                        String pictureUrl = (String) payload.get("picture");

                        // Lưu thông tin người dùng vào session hoặc xử lý tiếp theo
                        req.getSession().setAttribute("userName", name);
                        req.getSession().setAttribute("email", email);
                        req.getSession().setAttribute("pictureUrl", pictureUrl);

                        // Chuyển hướng người dùng đến trang chính sau khi đăng nhập thành công
                        resp.sendRedirect(req.getContextPath() + "/views/user/home.jsp");
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
        return jsonObject.get("id_token").getAsString();  // Lấy id_token từ JSON
    }
}

