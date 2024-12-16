package vn.HiepKa.services;

import java.sql.SQLException;

import vn.HiepKa.models.UserModel;

public interface IUserService {
	UserModel login(String email, String password);

	UserModel FindByEmail(String email);

	boolean register(String email, String username, String password);

	void sendResetToken(String email);

	void updatePasswordByToken(String newPassword, String resetToken) throws SQLException;

	boolean updatePasswordByEmail(String email, String newPassword);

}