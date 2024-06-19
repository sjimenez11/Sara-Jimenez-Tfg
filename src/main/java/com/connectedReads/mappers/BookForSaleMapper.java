package com.connectedReads.mappers;

import com.connectedReads.dtos.BookForSaleResponseDto;
import com.connectedReads.entities.BookForSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookForSaleMapper {
    private final BookMapper bookMapper;

    @Autowired
    public BookForSaleMapper(BookMapper bookMapper){
        this.bookMapper = bookMapper;
    }

    public BookForSaleResponseDto toResponseDto(BookForSale bookForSale){
        return new  BookForSaleResponseDto(
                bookForSale.getId(),
                bookForSale.getUser().getId(),
                bookMapper.toResponseDto(bookForSale.getBook()),
                bookForSale.getPrice(),
                bookForSale.getStatus(),
                bookForSale.getDescription()

        );
    }

    public List<BookForSaleResponseDto> toResponseDtoList(List<BookForSale> bookForSale) {
        return bookForSale.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
