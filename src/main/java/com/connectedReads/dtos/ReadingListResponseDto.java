package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingListResponseDto {
    private Long id;
    //TODO añadir usuarios
    private Long userId;
    private String name;
    private String description;
}
