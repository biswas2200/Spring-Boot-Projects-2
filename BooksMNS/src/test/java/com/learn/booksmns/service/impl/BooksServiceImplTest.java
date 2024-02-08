package com.learn.booksmns.service.impl;

import com.learn.booksmns.dto.BooksDTO;
import com.learn.booksmns.entity.Books;
import com.learn.booksmns.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BooksServiceImplTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BooksServiceImpl booksServiceImpl;

    @Test
    void test_getAllBooks() {
        List<Books> books = new ArrayList<>();
        books.add(new Books(1L,"Maven","Kito","Publishers",2002));
        books.add(new Books(2L,"Maven-II","Kito","Publishers",2005));
        when(bookRepository.findAll()).thenReturn(books);

        List<BooksDTO> allBooks = booksServiceImpl.getAllBooks();
        assertNotNull(books);
        assertEquals(2,allBooks.size());
        assertEquals("Maven",books.get(0).getBookName());
        assertEquals("Maven-II",books.get(1).getBookName());
        assertEquals(1L,books.get(0).getBookReferenceId());
        assertEquals(2L,books.get(1).getBookReferenceId());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void test_getBookByRefId() {
        Books books = new Books(1L,"Loner","Harry","Moon Publishers",2015);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(books));
        BooksDTO booksDTO = booksServiceImpl.getBookByRefId(1L);

        assertEquals(1L,booksDTO.getBookReferenceId());
        assertEquals("Loner", booksDTO.getBookName());
        assertEquals("Harry",booksDTO.getAuthorName());
        assertEquals("Moon Publishers", booksDTO.getPublisherName());
    }

    @Test
    void test_createBooks() {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setBookName("Loner");
        Books books = new Books(1L,"Loner","Harry","Moon Publisher",2015);
        when(bookRepository.save(any(Books.class))).thenReturn(books);
        BooksDTO dto = booksServiceImpl.createBooks(booksDTO);

        assertEquals("Loner",dto.getBookName());
        assertEquals(1L,dto.getBookReferenceId());
        assertEquals("Harry", dto.getAuthorName());
    }

    @Test
    void test_updateBooks() {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setAuthorName("Brad");

        Books existingBooks = new Books(1L,"Loner","Harry","Moon Publisher",2015);
        Books updatedBooks = new Books(1L,"Loner","Brad","Moon Publisher",2015);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBooks));
        when(bookRepository.save(any(Books.class))).thenReturn(updatedBooks);

        BooksDTO dto = booksServiceImpl.updateBooks(1L,booksDTO);

        assertEquals(1L,dto.getBookReferenceId());
        assertEquals("Brad",dto.getAuthorName());
    }

    @Test
    void deleteBooks() {
        booksServiceImpl.deleteBooks(1L);
        verify(bookRepository,times(1)).deleteById(1L);
    }
}