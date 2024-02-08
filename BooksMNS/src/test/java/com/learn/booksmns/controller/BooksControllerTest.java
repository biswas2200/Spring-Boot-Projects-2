package com.learn.booksmns.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.booksmns.dto.BooksDTO;
import com.learn.booksmns.service.BooksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
class BooksControllerTest {
    @MockBean
    BooksService booksService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void test_getAllBooks() throws Exception {
        mockMvc.perform(get("/books-details/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    void test_getBooksById() throws Exception {
        mockMvc.perform(get("/books-details/get/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    void test_saveBooks() throws Exception {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setBookReferenceId(1L);
        booksDTO.setBookName("Charlie Chaplin");
        booksDTO.setAuthorName("Robert Kent");
        booksDTO.setPublisherName("Penguins Production");
        booksDTO.setYearOfPublished(1978);
        when(booksService.createBooks(booksDTO)).thenReturn(booksDTO);
        mockMvc.perform(post("/books-details/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(booksDTO)))
                .andExpect(status().isCreated());

    }

    @Test
    void test_updateBooks() throws Exception {
        BooksDTO booksDTO = new BooksDTO();
        mockMvc.perform(put("/books-details/get/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(booksDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void test_deleteBooks() throws Exception {
        mockMvc.perform(delete("/books-details/get/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}