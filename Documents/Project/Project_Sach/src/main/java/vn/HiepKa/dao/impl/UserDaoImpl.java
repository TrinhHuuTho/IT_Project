package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        String usernameToFind = "DuyHao";
        UserModel user = userDao.findByUserName(usernameToFind);
        if (user != null) {
            System.out.println("Thông tin người dùng với username '" + usernameToFind + "':");
            System.out.println("ID: " + user.getUserid());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Role ID: " + user.getRoleid());
        } else {
            System.out.println("Không tìm thấy người dùng với username '" + usernameToFind + "'.");
        }
    }
	@Override
	public void insert(UserModel user) throws SQLException, Exception {
		String sql = "INSERT INTO USERS (username, email, password)"
				+ "VALUES (?, ?, ?)";

		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

//	public static void main(String[] args) {
//		UserDaoImpl userDao = new UserDaoImpl();
//		UserModel newUser = new UserModel( 6, "TrongThuc", "TrongThuc@gmail.com","123", 7);
//			try {
//				userDao.insert(newUser);
//				System.out.println("Đã chèn người dùng mới vào cơ sở dữ liệu.");
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("Lỗi");
//			}		
//	}
}
