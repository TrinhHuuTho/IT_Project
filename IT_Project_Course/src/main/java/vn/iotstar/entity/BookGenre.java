package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKGENRE")
public class BookGenre {

	@EmbeddedId
	private BookGenreKey id;
	/* Khóa chính cuẩ mối quan hệ nhiều - nhiều là một tập hợp
	 * gồm các thuộc tính tham gia vào mối quan hệ đó*/
	// Khóa chính gồm book_id và genre_id
	
	@ManyToOne
	@MapsId("genreId")
	@JoinColumn(name = "genre_id")
	private Genre genre;

	@ManyToOne
	@MapsId("bookId")
	@JoinColumn(name = "book_id")
	private Book book;
}
