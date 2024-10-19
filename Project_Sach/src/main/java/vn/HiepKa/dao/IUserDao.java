package vn.HiepKa.dao;

import java.sql.SQLException;

import vn.HiepKa.models.UserModel;

public interface IUserDao {
	UserModel findByUserName(String username);
	UserModel findByEmail(String email);
	void insert(UserModel user) throws SQLException, Exception;
	public void resetPassword(String email, String newPassword);
}
