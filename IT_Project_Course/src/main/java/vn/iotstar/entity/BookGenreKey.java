package vn.iotstar.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookGenreKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "book_id")
    private int bookId;
}
