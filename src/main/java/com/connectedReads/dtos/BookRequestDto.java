package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequestDto {
    private final String title;
    private final String author;
    private final String synopsis;
}
