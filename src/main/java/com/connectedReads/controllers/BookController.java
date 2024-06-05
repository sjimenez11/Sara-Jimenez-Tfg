package com.connectedReads.controllers;

import com.connectedReads.dtos.BookRequestDto;
import com.connectedReads.dtos.BookResponseDto;
import com.connectedReads.entities.BookEntity;
import com.connectedReads.mappers.BookMapper;
import com.connectedReads.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto){
        BookEntity bookSaved = bookService.createBook(bookMapper.toModel(bookRequestDto));
        return ResponseEntity.created(null).body(bookMapper.toResponse(bookSaved));
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        return ResponseEntity.ok(bookMapper.toResponse(bookService.getAllBooks()));
    }

    @GetMapping("/{id}")
    public BookEntity findBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(
            @PathVariable String title
    ) {
        return ResponseEntity.ok(bookMapper.toResponse(bookService.findBooksByTitle(title))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }
}
