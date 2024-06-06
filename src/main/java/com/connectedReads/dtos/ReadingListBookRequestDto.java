package com.connectedReads.dtos;

import com.connectedReads.entities.BookEntity;
import com.connectedReads.entities.ReadingListEntity;
import com.connectedReads.entities.enums.ReadingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingListBookRequestDto {
    private final ReadingListEntity readingList;
    private final BookEntity book;
    private final ReadingStatus status;
}
