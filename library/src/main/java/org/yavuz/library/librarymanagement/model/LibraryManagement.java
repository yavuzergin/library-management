package org.yavuz.library.librarymanagement.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yavuz.library.books.model.Book;
import org.yavuz.library.members.model.Member;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "LibraryManagement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "borrow_duration")
    private int borrowDuration;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status")
    private String status;

}
