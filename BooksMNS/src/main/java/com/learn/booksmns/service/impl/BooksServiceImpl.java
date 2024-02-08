package com.learn.booksmns.service.impl;

import com.learn.booksmns.dto.BooksDTO;
import com.learn.booksmns.entity.Books;
import com.learn.booksmns.exception.BookNotFoundException;
import com.learn.booksmns.repository.BookRepository;
import com.learn.booksmns.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {

    private BooksDTO convertToDTO(Books books) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setBookReferenceId(books.getBookReferenceId());
        booksDTO.setBookName(books.getBookName());
        booksDTO.setAuthorName(books.getAuthorName());
        booksDTO.setPublisherName(books.getPublisherName());
        booksDTO.setYearOfPublished(books.getYearOfPublished());
        return booksDTO;
    }

    private Books convertToEntity(BooksDTO booksDTO) {
        Books books = new Books();
        books.setBookReferenceId(booksDTO.getBookReferenceId());
        books.setBookName(booksDTO.getBookName());
        books.setAuthorName(booksDTO.getAuthorName());
        books.setPublisherName(booksDTO.getPublisherName());
        books.setYearOfPublished(booksDTO.getYearOfPublished());
        return books;
    }

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BooksDTO> getAllBooks() {
        List<Books> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BooksDTO getBookByRefId(Long referenceId) {
        Books books = bookRepository.findById(referenceId)
                .orElseThrow(() -> new BookNotFoundException("Book not found" + referenceId));
        return convertToDTO(books);
    }

    @Override
    public BooksDTO createBooks(BooksDTO booksDTO) {
        Books books = convertToEntity(booksDTO);
        Books savedBooks = bookRepository.save(books);
        return convertToDTO(savedBooks);
    }

    @Override
    public BooksDTO updateBooks(Long referenceId, BooksDTO booksDTO) {
        Books books = bookRepository.findById(referenceId)
                .orElseThrow(() -> new BookNotFoundException("Book not found" + referenceId));
        books.setBookName(books.getBookName());
        books.setAuthorName(books.getAuthorName());
        books.setPublisherName(books.getPublisherName());
        books.setYearOfPublished(booksDTO.getYearOfPublished());
        Books updateBooks = bookRepository.save(books);
        return convertToDTO(updateBooks);
    }

    @Override
    public void deleteBooks(Long referenceId) {
        bookRepository.deleteById(referenceId);
    }
}
