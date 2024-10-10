package vn.iotstar.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "USERS")  // Điều chỉnh tên bảng cho đúng với SQL
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")  // Sửa lại đúng với tên trường SQL là "users_id"
	private int usersId;  // Thay đổi tên biến để phù hợp với quy ước Java

	@Column(name = "username", columnDefinition = "nvarchar(50) not null unique")
	@NotEmpty(message = "Không được phép rỗng")
	private String username;

	@Column(name = "email", columnDefinition = "nvarchar(100) unique")
	private String email;  // Email có thể để trống

	@Column(name = "password", columnDefinition = "nvarchar(64) not null")
	@NotEmpty(message = "Mật khẩu không được để trống")
	private String password;

	@Column(name = "roles", columnDefinition = "int default 1")
	private int roles = 1;

	// Tạm thời chưa cần dùng đến
//	@Column(name = "images", columnDefinition = "nvarchar(500) null")
//	private String images;
//
//	@Column(name = "status")
//	private int status;
}
