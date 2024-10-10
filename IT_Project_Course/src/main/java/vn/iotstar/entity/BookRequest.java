package vn.iotstar.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BOOK_REQUEST")
public class BookRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "title", columnDefinition = "nvarchar(200) not null")
    private String title;

    @Column(name = "content", columnDefinition = "nvarchar(100) not null")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Lob
    @Column(name = "images_book", columnDefinition = "varbinary(max)")
    private byte[] imagesBook;

    @Column(name = "status", columnDefinition = "nvarchar(20) check (status in ('pending', 'accepted', 'rejected')) default 'pending'")
    private String status;
}
