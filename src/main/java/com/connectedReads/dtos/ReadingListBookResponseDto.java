package com.connectedReads.dtos;

import com.connectedReads.entities.Book;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.enums.ReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingListBookResponseDto {
    private Long id;
    private BookResponseDto book;
    private ReadingListResponseDto readingList;
    private ReadingStatus status;
}
