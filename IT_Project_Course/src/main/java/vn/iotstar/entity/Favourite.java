package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FAVOURITE")
public class Favourite {

	@EmbeddedId
	private FavouriteKey id;
	/* Khóa chính cuẩ mối quan hệ nhiều - nhiều là một tập hợp
	 * gồm các thuộc tính tham gia vào mối quan hệ đó*/
	// Khóa chính gồm user_id và book_id
	
	@ManyToOne
	@MapsId("usersId")
	@JoinColumn(name = "users_id")
	private User user;

	@ManyToOne
	@MapsId("bookId")
	@JoinColumn(name = "book_id")
	private Book book;
}
