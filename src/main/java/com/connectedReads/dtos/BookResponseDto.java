package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String synopsis;
    private Set<ReadingListBookResponseDto> readingListBooks;
    private Set<ReviewResponseDto> reviews;
    private Set<BookForSaleResponseDto> booksForSale;
}
