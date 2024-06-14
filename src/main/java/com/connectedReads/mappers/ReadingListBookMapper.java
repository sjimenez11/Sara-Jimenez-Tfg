package com.connectedReads.mappers;

import com.connectedReads.dtos.ReadingListBookResponseDto;
import com.connectedReads.entities.ReadingListBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReadingListBookMapper {
    private final BookMapper bookMapper;
    private final ReadingListMapper readingListMapper;

    @Autowired
    public ReadingListBookMapper(BookMapper bookMapper, ReadingListMapper readingListMapper) {
        this.bookMapper = bookMapper;
        this.readingListMapper = readingListMapper;
    }

    public ReadingListBookResponseDto toResponseDto(ReadingListBook readingListBook) {
        return new ReadingListBookResponseDto(
                readingListBook.getId(),
                bookMapper.toResponseDto(readingListBook.getBook()),
                readingListMapper.toResponseDto(readingListBook.getReadingList()),
                readingListBook.getStatus()
        );
    }

    public List<ReadingListBookResponseDto> toResponseDtoList(List<ReadingListBook> readingListBooks) {
        return readingListBooks.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
