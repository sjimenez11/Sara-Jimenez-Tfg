package com.connectedReads.controllers;

import com.connectedReads.dtos.BookForSaleRequestDto;
import com.connectedReads.dtos.BookForSaleResponseDto;
import com.connectedReads.dtos.ReadingListBookResponseDto;
import com.connectedReads.entities.BookForSale;
import com.connectedReads.entities.ReadingListBook;
import com.connectedReads.entities.enums.ReadingStatus;
import com.connectedReads.entities.enums.SaleStatus;
import com.connectedReads.mappers.BookForSaleMapper;
import com.connectedReads.services.BookForSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/booksForSale")
public class BookForSaleController {
    @Autowired
    private BookForSaleService bookForSaleService;

    @Autowired
    private BookForSaleMapper bookForSaleMapper;

    @PostMapping
    public ResponseEntity<BookForSaleResponseDto> createBookForSale (
            @RequestBody BookForSaleRequestDto bfsRequestDto
    ){
        BookForSale bookForSale = bookForSaleService.createBookForSale(bfsRequestDto);
        BookForSaleResponseDto bfsResponseDto = bookForSaleMapper.toResponseDto(bookForSale);
        return ResponseEntity.created(null).body(bfsResponseDto);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<BookForSaleResponseDto> updateBookForSaleByPrice(
            @PathVariable Long id, @RequestBody double price
    ){
        BookForSale bookForSale = bookForSaleService.updateBookForSaleByPrice(id, price);
        BookForSaleResponseDto bfsResponseDto = bookForSaleMapper.toResponseDto(bookForSale);
        return ResponseEntity.ok(bfsResponseDto);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookForSaleResponseDto> updateBookForSaleStatus(
            @PathVariable Long id, @RequestBody SaleStatus status
    ){
        BookForSale bookForSale = bookForSaleService.updateBookForSaleByStatus(id, status);
        BookForSaleResponseDto bfsResponseDto = bookForSaleMapper.toResponseDto(bookForSale);
        return ResponseEntity.ok(bfsResponseDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookForSaleResponseDto>> findBooksForSaleByUser(@PathVariable Long userId) {
        List<BookForSale> booksForSale = bookForSaleService.findBooksForSaleByUser(userId);

        List<BookForSaleResponseDto> bfsResponseDtos = booksForSale.stream()
                .map(bookForSaleMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bfsResponseDtos);
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookForSaleResponseDto>> findBooksForSaleByTitle(@RequestParam String title) {
        List<BookForSale> booksForSale = bookForSaleService.findBooksForSaleByTitle(title);

        List<BookForSaleResponseDto> bfsResponseDtos = booksForSale.stream()
                .map(bookForSaleMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bfsResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookForSaleById(@PathVariable Long id) {
        bookForSaleService.deleteBookForSaleById(id);
        return ResponseEntity.noContent().build();
    }
}
