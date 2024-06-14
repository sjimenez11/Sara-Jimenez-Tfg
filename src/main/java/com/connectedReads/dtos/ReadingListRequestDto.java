package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadingListRequestDto {
    //TODO añadir usuarios
    private final Long userId;
    private final String name;
    private final String description;
}
