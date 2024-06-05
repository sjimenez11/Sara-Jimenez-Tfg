package com.connectedReads.controllers;

import com.connectedReads.entities.BookEntity;
import com.connectedReads.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public BookEntity createBook(@RequestBody BookEntity book){
        return bookService.createBook(book);
    }

    @GetMapping("/books")
    public List<BookEntity> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public BookEntity findBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }
}
