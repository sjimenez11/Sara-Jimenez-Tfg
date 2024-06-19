package com.connectedReads.dtos;

import com.connectedReads.entities.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookForSaleRequestDto {
    private final Long userId;
    private final Long bookId;
    private final double price;
    private final SaleStatus status;
    private final String description;
}
