package vn.HiepKa.services.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

import vn.HiepKa.dao.IUserDao;
import vn.HiepKa.dao.impl.UserDaoImpl;
import vn.HiepKa.models.UserModel;
import vn.HiepKa.services.IUserService;
import vn.HiepKa.utils.HashPasswordUtils;

public class UserService implements IUserService {
	IUserDao userDao = new UserDaoImpl();
		
	@Override
	public UserModel login(String email, String password) {
		String hashedpass = HashPasswordUtils.hashPasswordWithSHA256(password);
		UserModel user = this.FindByEmail(email);
		if (user != null && hashedpass.trim().equals(user.getPassword().trim())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public boolean register(String email, String username, String password) {
		UserModel existingUser = userDao.findByEmail(email);
		if (existingUser != null) {
			return false;
		}
		
		// Kiểm tra nếu password là null, đặt mật khẩu trống hoặc mật khẩu tạm
	    if (password == null) {
	        password = "123456"; // hoặc sử dụng mật khẩu tạm thời khác
	    }
	    
		UserModel newUser = new UserModel();
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setPassword(HashPasswordUtils.hashPasswordWithSHA256(password));
		try {
			userDao.insert(newUser);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void sendResetToken(String email) {
		
		// Cài đặt múi giờ đồng bộ giữa database và java
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		System.out.println("Thời gian hiện tại (Việt Nam - ứng dụng): " + new Timestamp(System.currentTimeMillis()));
		
	    UserModel user = userDao.findByEmail(email);
	    if (user == null) {
	        return; // Người dùng không tồn tại
	    }

	    // Tạo token và thời gian hết hạn
	    String token = UUID.randomUUID().toString();
	    Timestamp expiry = calculateExpiryDate(60); // 60 phút

	    try {
	        userDao.updateResetToken(email, token, expiry);
	        // Gọi phương thức gửi email
	        EmailService emailService = new EmailService();
	        emailService.sendPasswordResetEmail(email, token);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
	    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  // Đảm bảo sử dụng UTC
	    calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
	    return new Timestamp(calendar.getTimeInMillis());
	}

	@Override
	public void updatePasswordByToken(String newPassword, String resetToken) throws SQLException {
		
		System.out.println("Token nhận được trong service: " + resetToken);
		System.out.println("Pasword nhận được trong service: " + newPassword);

	    if (resetToken != null) {
	        // Kiểm tra tính hợp lệ của token
	        boolean isValid = userDao.isResetTokenValid(resetToken);
	        if (!isValid) {
	            throw new IllegalArgumentException("Token không hợp lệ hoặc đã hết hạn.");
	        }
	    }

	    // Mã hóa mật khẩu mới
	    String hashedPassword = HashPasswordUtils.hashPasswordWithSHA256(newPassword);

	    // Thực hiện cập nhật mật khẩu
	    userDao.updatePasswordByToken(resetToken, hashedPassword);
	}
	
	@Override
	public boolean updatePasswordByEmail(String email, String newPassword) {
	    UserModel user = userDao.findByEmail(email);
	    if (user != null) {
	        user.setPassword(HashPasswordUtils.hashPasswordWithSHA256(newPassword));
	        try {
	            userDao.updatePasswordByEmail(user); // Cập nhật mật khẩu vào cơ sở dữ liệu
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
}