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
public class FavouriteKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "users_id")
    private int usersId;

    @Column(name = "book_id")
    private int bookId;
}
