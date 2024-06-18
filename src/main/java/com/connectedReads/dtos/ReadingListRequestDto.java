package com.connectedReads.dtos;

import com.connectedReads.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ReadingListRequestDto {
    private final Long userId;
    private final String name;
    private final String description;
    private Set<Long> bookIds;
}
