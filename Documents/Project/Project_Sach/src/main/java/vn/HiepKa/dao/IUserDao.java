package vn.HiepKa.dao;

import java.sql.SQLException;

import vn.HiepKa.models.UserModel;

public interface IUserDao {
	UserModel findByUserName(String username);
	void insert(UserModel user) throws SQLException, Exception;
}
