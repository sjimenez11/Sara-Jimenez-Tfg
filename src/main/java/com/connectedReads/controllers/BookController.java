package com.connectedReads.controllers;

import com.connectedReads.dtos.BookRequestDto;
import com.connectedReads.dtos.BookResponseDto;
import com.connectedReads.dtos.MessageResponseDto;
import com.connectedReads.entities.Book;
import com.connectedReads.entities.Message;
import com.connectedReads.mappers.BookMapper;
import com.connectedReads.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> createBook(
            @RequestBody BookRequestDto bookRequestDto
    ) {
        Book book= bookService.createBook(bookRequestDto);
        BookResponseDto bookResponseDto = bookMapper.toResponseDto(book);
        return ResponseEntity.created(null).body(bookResponseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResponseDto> bookResponseDtos = bookMapper.toResponseDtoList(books);
        return ResponseEntity.ok(bookResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            BookResponseDto bookResponseDto = bookMapper.toResponseDto(book);
            return ResponseEntity.ok(bookResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(@PathVariable String title) {
        List<Book> books = bookService.findBooksByTitle(title);
        List<BookResponseDto> bookResponseDtos = bookMapper.toResponseDtoList(books);
        return ResponseEntity.ok(bookResponseDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
