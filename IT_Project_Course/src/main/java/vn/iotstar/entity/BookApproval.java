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
@Table(name = "BOOK_APPROVAL")
public class BookApproval implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_id")
    private int approvalId;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private BookRequest bookRequest;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    @Column(name = "decision", columnDefinition = "nvarchar(20) not null check (decision in ('accepted', 'rejected'))")
    private String decision;

    @Column(name = "approval_date", nullable = false)
    private LocalDate approvalDate;
}
