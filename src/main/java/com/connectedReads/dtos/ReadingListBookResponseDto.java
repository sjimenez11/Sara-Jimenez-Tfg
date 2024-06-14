package com.connectedReads.dtos;

import com.connectedReads.entities.BookEntity;
import com.connectedReads.entities.ReadingListEntity;
import com.connectedReads.entities.enums.ReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingListBookResponseDto {
    private Long id;
    private ReadingListEntity readingList;
    private BookEntity book;
    private ReadingStatus status;
}
