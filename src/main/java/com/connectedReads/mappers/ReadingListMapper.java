package com.connectedReads.mappers;

import com.connectedReads.dtos.ReadingListBookResponseDto;
import com.connectedReads.dtos.ReadingListRequestDto;
import com.connectedReads.dtos.ReadingListResponseDto;
import com.connectedReads.entities.ReadingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ReadingListMapper {
    private final ReadingListBookMapper readingListBookMapper;

    @Autowired
    public ReadingListMapper(@Lazy ReadingListBookMapper readingListBookMapper) {
        this.readingListBookMapper = readingListBookMapper;
    }

    public ReadingList toEntity(ReadingListRequestDto readingListRequestDto) {
        ReadingList readingList = new ReadingList();
        readingList.setUserId(readingListRequestDto.getUserId());
        readingList.setName(readingListRequestDto.getName());
        readingList.setDescription(readingListRequestDto.getDescription());

        return readingList;
    }

    public ReadingListResponseDto toResponseDto(ReadingList readingList) {
        List<ReadingListBookResponseDto> readingListBooks = readingList.getReadingListBooks().stream()
                .map(readingListBookMapper::toResponseDto)
                .toList();

        return new ReadingListResponseDto(
                readingList.getId(),
                readingList.getUserId(),
                readingList.getName(),
                readingList.getDescription(),
                new HashSet<>(readingListBooks)
        );
    }

    public List<ReadingListResponseDto> toResponseDtoList(List<ReadingList> readingLists) {
        return readingLists.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
