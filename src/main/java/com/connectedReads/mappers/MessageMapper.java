package com.connectedReads.mappers;

import com.connectedReads.dtos.MessageResponseDto;
import com.connectedReads.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    private final UserMapper userMapper;

    @Autowired
    public MessageMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public MessageResponseDto toResponseDto(Message message){
        return new MessageResponseDto(
                message.getId(),
                userMapper.toDto(message.getSender()),
                userMapper.toDto(message.getRecipient()),
                message.getContent(),
                message.getTimestamp()
        );
    }

    public List<MessageResponseDto> toResponseDtoList(List<Message> messages){
        return messages.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
