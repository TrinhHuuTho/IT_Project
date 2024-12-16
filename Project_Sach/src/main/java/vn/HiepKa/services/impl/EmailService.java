package vn.HiepKa.services.impl;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

	public void sendPasswordResetEmail(String email, String token) {
		final String username = "trinhuutho@gmail.com"; // Địa chỉ email
		final String password = "wrwqwpdmtyyinknh"; // Mật khẩu ứng dụng gmail

		// Thiết lập các thuộc tính
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.port", "587");

		// Tạo một phiên
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Tạo một đối tượng Message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("TomeKeeper-Khôi phục mật khẩu");
			// Nội dung email dưới dạng HTML
			String emailContent = """
			    <html>
			    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
			        <div style="max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;">
			            <!-- Tiêu đề -->
			            <h2 style="color: #2C3E50; text-align: center;">TomeKeeper</h2>
			            <p>Dear <b>""" + email + """ 
			            		
			            		</b>,
			            		</p>
			            <p>Gần đây bạn đã yêu cầu đặt lại mật khẩu cho tài khoản TomeKeeper của mình.</p>
			            <p>Để khôi phục mật khẩu của bạn, hãy nhấp vào nút bên dưới và đặt mật khẩu mới:</p>
			            <!-- Nút đặt lại mật khẩu -->
			            <div style="text-align: center; margin: 20px 0;">
			                <a href="https://localhost:8443/Project_Sach/authentication/reset-password?action=reset&token=""" + token + """
			                " style="background-color: #3498DB; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; display: inline-block;">
			                    Reset Password
			                </a>
			            </div>
			            <p>Nếu bạn không yêu cầu điều này, bạn có thể yên tâm bỏ qua email này.</p>
			            <p>Cảm ơn,<br>Nhóm TomeKeeper</p>
			            <hr style="margin-top: 30px;"/>
			            <!-- URL dự phòng -->
			            <p style="font-size: 12px; color: #7F8C8D;">
			               Nếu bạn gặp sự cố khi nhấp vào nút, hãy sao chép và dán URL bên dưới vào trình duyệt web của bạn:
			                <br>
			                <a href="https://localhost:8443/Project_Sach/authentication/reset-password?action=reset&token=""" + token + """
			                    style="color: #3498DB; word-wrap: break-word;">
			                    https://localhost:8443/Project_Sach/authentication/reset-password?action=reset&token=""" + token + """
			                </a>
			            </p>
			        </div>
			    </body>
			    </html>
			""";
			// Thiết lập email dưới dạng HTML
			message.setContent(emailContent, "text/html; charset=UTF-8");
			// Gửi email
			Transport.send(message);
			System.out.println("Email đã được gửi thành công");

		} catch (MessagingException e) {
			System.err.println("Lỗi khi gửi email: " + e.getMessage());
			e.printStackTrace(); // In ra thông tin lỗi chi tiết
		}
	}

}
