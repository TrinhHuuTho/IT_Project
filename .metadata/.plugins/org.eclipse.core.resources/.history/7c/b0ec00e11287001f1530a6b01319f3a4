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
@Table(name = "USER")
@NamedQuery(name = "USER.findAll", query = "SELECT c FROM USER c")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;

	@Column(name = "username", columnDefinition = "nvarchar(50) not null unique")
	@NotEmpty(message = "Không được phép rỗng")
	private String username;

	@Column(name = "Images", columnDefinition = "nvarchar(500) null")
	private String images;
	private int status;

	// bi-directional many-to-one association to Video

	// @OneToMany(mappedBy = "category")
}
