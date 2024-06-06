package com.connectedReads.mappers;

import com.connectedReads.dtos.ReadingListRequestDto;
import com.connectedReads.dtos.ReadingListResponseDto;
import com.connectedReads.entities.ReadingListEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadingListMapper {
    public ReadingListResponseDto toResponse(ReadingListEntity readingList) {
        return new ReadingListResponseDto(
                readingList.getId(),
                readingList.getUserId(),
                readingList.getName(),
                readingList.getDescription()
        );
    }

    public List<ReadingListResponseDto> toResponse(List<ReadingListEntity> readingList) {
        return readingList.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public ReadingListEntity toModel(ReadingListRequestDto readingListRequestDto) {
        return new ReadingListEntity(
                0L,
                readingListRequestDto.getUserId(),
                readingListRequestDto.getName(),
                readingListRequestDto.getDescription()
        );
    }
}
