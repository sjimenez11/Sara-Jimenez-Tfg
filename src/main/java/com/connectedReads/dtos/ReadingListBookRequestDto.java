package com.connectedReads.dtos;

import com.connectedReads.entities.Book;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.enums.ReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingListBookRequestDto {
    private final Long idReadingList;
    private final Long idBook;
    private final ReadingStatus status;
}
