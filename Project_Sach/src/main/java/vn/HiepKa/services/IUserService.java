package vn.HiepKa.services;

import vn.HiepKa.models.UserModel;

public interface IUserService {
	UserModel login(String email, String password);
	UserModel FindByEmail(String email);
	boolean register ( String email, String username, String password);
}
