package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import vn.HiepKa.configs.DBConnectSQL;
import vn.HiepKa.dao.IUserDao;
import vn.HiepKa.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public UserModel findByUserName(String username) {
		String sql = "select * from USERS where username = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setUserid(rs.getInt("users_id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRoleid(Integer.parseInt(rs.getString("roles")));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) throws SQLException, Exception {
		String sql = "INSERT INTO USERS (username, email, password)" + "VALUES (?, ?, ?)";
		try {
			new DBConnectSQL();
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel findByEmail(String email) {
		String sql = "select * from USERS where email = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setUserid(rs.getInt("users_id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRoleid(Integer.parseInt(rs.getString("roles")));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateResetToken(String email, String token, Timestamp expiry) throws SQLException {
	    String sql = "UPDATE users SET reset_token = ?, reset_token_expiry = ? WHERE email = ?";
	    try (Connection conn = super.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, token); // Cột reset_token
	        stmt.setTimestamp(2, expiry); // Cột reset_token_expiry
	        stmt.setString(3, email); // Email người dùng
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public boolean isResetTokenValid(String resetToken) {
	    String sql = "SELECT reset_token_expiry FROM users WHERE reset_token = ?";
	    try (Connection conn = super.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, resetToken);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            Timestamp expiryDate = rs.getTimestamp("reset_token_expiry");
	            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	            // In ra log chi tiết về thời gian hiện tại và thời gian hết hạn
	            System.out.println("Thời gian hiện tại (UTC): " + currentTime);
	            System.out.println("Thời gian hết hạn từ DB (UTC): " + expiryDate);
	            // So sánh và in ra kết quả
	            if (expiryDate != null && expiryDate.after(currentTime)) {
	                System.out.println("Token hợp lệ");
	                return true;
	            } else {
	                System.out.println("Token không hợp lệ hoặc đã hết hạn");
	                return false;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false; // Token không hợp lệ hoặc đã hết hạn
	}


	@Override
	public void updatePasswordByToken(String resetToken, String hashedPassword) throws SQLException {
	    String sql = "UPDATE users SET password = ?, reset_token = NULL, reset_token_expiry = NULL WHERE reset_token = ?";
	    try (Connection conn = super.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, hashedPassword); // Đặt mật khẩu băm vào vị trí đầu tiên
	        ps.setString(2, resetToken); // Đặt token vào vị trí thứ hai
	        int rowsAffected = ps.executeUpdate(); // Thực hiện cập nhật và lấy số hàng bị ảnh hưởng
	        conn.commit(); // Commit để đảm bảo thay đổi được lưu
	        System.out.println("Số hàng bị ảnh hưởng: " + rowsAffected); // Log kiểm tra
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new SQLException("Cập nhật mật khẩu thất bại.");
	    }
	}
	
	@Override
	public void updatePasswordByEmail(UserModel user) throws SQLException {
	    String sql = "UPDATE USERS SET password = ? WHERE email = ?";
	    try (Connection conn = super.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, user.getPassword());
	        ps.setString(2, user.getEmail());
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}