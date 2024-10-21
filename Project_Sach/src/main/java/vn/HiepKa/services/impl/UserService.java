package vn.HiepKa.services.impl;

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

	public boolean resetPassword(String email, String newPassword) {
		try {
			userDao.resetPassword(email, newPassword); // Cập nhật mật khẩu trong cơ sở dữ liệu
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}