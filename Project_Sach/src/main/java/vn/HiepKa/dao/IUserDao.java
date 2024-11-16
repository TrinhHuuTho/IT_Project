package vn.HiepKa.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import vn.HiepKa.models.UserModel;

public interface IUserDao {

	UserModel findByUserName(String username);

	UserModel findByEmail(String email);

	void insert(UserModel user) throws SQLException, Exception;

	boolean isResetTokenValid(String token) throws SQLException;

	void updatePasswordByToken(String token, String hashedPassword) throws SQLException;

	void updateResetToken(String email, String token, Timestamp expiry) throws SQLException;

	void updatePasswordByEmail(UserModel user) throws SQLException;

}
