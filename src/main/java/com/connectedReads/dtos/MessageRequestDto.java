package com.connectedReads.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageRequestDto {
    private final Long senderId;
    private final Long recipientId;
    private final String content;
}
