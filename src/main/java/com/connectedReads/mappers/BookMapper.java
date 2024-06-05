package com.connectedReads.mappers;

import com.connectedReads.dtos.BookRequestDto;
import com.connectedReads.dtos.BookResponseDto;
import com.connectedReads.entities.BookEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookMapper {
    public BookResponseDto toResponse(BookEntity book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getSynopsis()
        );
    }

    public List<BookResponseDto> toResponse(List<BookEntity> book) {
        return book.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public BookEntity toModel(BookRequestDto bookRequestDto) {
        return new BookEntity(
                0L,
                bookRequestDto.getTitle(),
                bookRequestDto.getAuthor(),
                bookRequestDto.getSynopsis()
        );
    }
}
