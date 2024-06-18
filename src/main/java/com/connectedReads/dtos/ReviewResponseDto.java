package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private BookResponseDto book;
    private String comment;
    private double rating;
}
