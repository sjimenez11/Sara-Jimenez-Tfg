package com.connectedReads.mappers;

import com.connectedReads.dtos.BookRequestDto;
import com.connectedReads.dtos.BookResponseDto;
import com.connectedReads.dtos.ReadingListBookResponseDto;
import com.connectedReads.dtos.ReviewResponseDto;
import com.connectedReads.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final ReadingListBookMapper readingListBookMapper;
    private final ReviewMapper reviewMapper;

    @Autowired
    public BookMapper(@Lazy ReadingListBookMapper readingListBookMapper, @Lazy ReviewMapper reviewMapper) {
        this.readingListBookMapper = readingListBookMapper;
        this.reviewMapper = reviewMapper;
    }

    public Book toEntity(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setSynopsis(bookRequestDto.getSynopsis());
        return book;
    }

    public BookResponseDto toResponseDto(Book book) {
        List<ReadingListBookResponseDto> readingListBooks = book.getReadingListBooks().stream()
                .map(readingListBookMapper::toResponseDto)
                .toList();

        List<ReviewResponseDto> reviews = book.getReviews().stream()
                .map(reviewMapper::toResponseDto)
                .toList();

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getSynopsis(),
                new HashSet<>(readingListBooks),
                new HashSet<>(reviews)
        );
    }

    public List<BookResponseDto> toResponseDtoList(List<Book> books) {
        return books.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
