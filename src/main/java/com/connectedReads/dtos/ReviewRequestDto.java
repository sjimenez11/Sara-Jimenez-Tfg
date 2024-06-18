package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewRequestDto {
    private final Long userId;
    private final Long bookId;
    private final String comment;
    private final double rating;
}
