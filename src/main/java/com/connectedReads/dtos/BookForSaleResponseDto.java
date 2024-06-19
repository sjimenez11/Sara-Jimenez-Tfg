package com.connectedReads.dtos;

import com.connectedReads.entities.Book;
import com.connectedReads.entities.User;
import com.connectedReads.entities.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookForSaleResponseDto {
    private Long id;
    private Long userId;
    private BookResponseDto book;
    private double price;
    private SaleStatus status;
    private String description;
}
