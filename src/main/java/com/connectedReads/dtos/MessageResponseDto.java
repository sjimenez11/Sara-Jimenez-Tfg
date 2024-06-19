package com.connectedReads.dtos;

import com.connectedReads.dtos.User.UserDto;
import com.connectedReads.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageResponseDto {
    private Long id;
    private UserDto sender;
    private UserDto recipient;
    private String content;
    private LocalDateTime timestamp;
}
