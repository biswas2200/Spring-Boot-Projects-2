package com.learn.booksmns.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books-details-info")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookReferenceId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "year_of_published")
    private Integer yearOfPublished;

}
