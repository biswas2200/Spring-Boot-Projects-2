package com.learn.booksmns.controller;

import com.learn.booksmns.dto.BooksDTO;
import com.learn.booksmns.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books-details")
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/get")
    public ResponseEntity<List<BooksDTO>> getAllBooks() {
        return new ResponseEntity<>(booksService.getAllBooks(), HttpStatus.FOUND);
    }

    @GetMapping("/get/{refId}")
    public ResponseEntity<BooksDTO> getBooksById(@PathVariable Long refId) {
        return new ResponseEntity<>(booksService.getBookByRefId(refId), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<BooksDTO> createBooks(@RequestBody BooksDTO booksDTO) {
        return new ResponseEntity<>(booksService.createBooks(booksDTO),HttpStatus.CREATED);
    }

    @PutMapping("/get/{refId}")
    public ResponseEntity<BooksDTO> updateBooks(@PathVariable Long refId,
                                                @RequestBody BooksDTO booksDTO) {
        return new ResponseEntity<>(booksService.updateBooks(refId,booksDTO),HttpStatus.OK);
    }
    @DeleteMapping("/get/{refId}")
    public void deleteBooks(@PathVariable Long refId){
        booksService.deleteBooks(refId);
    }
}
