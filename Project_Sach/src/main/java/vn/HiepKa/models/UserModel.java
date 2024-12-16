package vn.HiepKa.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userid;
	private String username;
	private String email;
	private String password;
	private int roleid;
	private String resettoken;
	private Timestamp resettokenexpiry;
	
	public UserModel() {
		super();
	}

	public UserModel(int userid, String username, String email, String password, int roleid, String resettoken,
			Timestamp resettokenexpiry) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roleid = roleid;
		this.resettoken = resettoken;
		this.resettokenexpiry = resettokenexpiry;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getResettoken() {
		return resettoken;
	}

	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}

	public Timestamp getResettokenexpiry() {
		return resettokenexpiry;
	}

	public void setResettokenexpiry(Timestamp timestamp) {
		this.resettokenexpiry = timestamp;
	}

	@Override
	public String toString() {
		return "UserModel [userid=" + userid + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", roleid=" + roleid + ", resettoken=" + resettoken + ", resettokenexpiry=" + resettokenexpiry + "]";
	}
	
}

