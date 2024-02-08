package com.learn.booksmns.service;

import com.learn.booksmns.dto.BooksDTO;

import java.util.List;

public interface BooksService {
    public List<BooksDTO> getAllBooks();
    public BooksDTO getBookByRefId(Long referenceId);
    public BooksDTO createBooks(BooksDTO booksDTO);
    public BooksDTO updateBooks(Long referenceId, BooksDTO booksDTO);
    public void deleteBooks(Long referenceId);

}
