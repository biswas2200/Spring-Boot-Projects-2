package com.learn.booksmns.dto;

import lombok.Data;

@Data
public class BooksDTO {
    private Long bookReferenceId;
    private String bookName;
    private String authorName;
    private String publisherName;
    private Integer yearOfPublished;
}
