package com.connectedReads.mappers;

import com.connectedReads.dtos.*;
import com.connectedReads.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final ReadingListBookMapper readingListBookMapper;
    private final ReviewMapper reviewMapper;
    private final BookForSaleMapper bookForSaleMapper;

    @Autowired
    public BookMapper(@Lazy ReadingListBookMapper readingListBookMapper, @Lazy ReviewMapper reviewMapper, @Lazy BookForSaleMapper bookForSaleMapper) {
        this.readingListBookMapper = readingListBookMapper;
        this.reviewMapper = reviewMapper;
        this.bookForSaleMapper = bookForSaleMapper;
    }

    public Book toEntity(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setSynopsis(bookRequestDto.getSynopsis());
        return book;
    }

    public BookResponseDto toResponseDto(Book book) {
        List<ReadingListBookResponseDto> readingListBooks = Optional.ofNullable(book.getReadingListBooks())
                .orElse(Collections.emptySet())
                .stream()
                .map(readingListBookMapper::toResponseDto)
                .toList();

        List<ReviewResponseDto> reviews = Optional.ofNullable(book.getReviews())
                .orElse(Collections.emptySet())
                .stream()
                .map(reviewMapper::toResponseDto)
                .toList();

        List<BookForSaleResponseDto> booksForSale = Optional.ofNullable(book.getBooksForSale())
                .orElse(Collections.emptySet())
                .stream()
                .map(bookForSaleMapper::toResponseDto)
                .toList();

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getSynopsis(),
                new HashSet<>(readingListBooks),
                new HashSet<>(reviews),
                new HashSet<>(booksForSale)
        );
    }

    public List<BookResponseDto> toResponseDtoList(List<Book> books) {
        return books.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
