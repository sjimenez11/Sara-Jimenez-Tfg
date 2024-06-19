package com.connectedReads.controllers;

import com.connectedReads.dtos.MessageRequestDto;
import com.connectedReads.dtos.MessageResponseDto;
import com.connectedReads.entities.Message;
import com.connectedReads.mappers.MessageMapper;
import com.connectedReads.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @PostMapping
    public ResponseEntity<MessageResponseDto> createMessage(
            @RequestBody MessageRequestDto messageRequestDto
    ){
        Message message = messageService.createMessage(messageRequestDto);
        MessageResponseDto messageResponseDto = messageMapper.toResponseDto(message);
        return ResponseEntity.created(null).body(messageResponseDto);
    }

    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<MessageResponseDto>> getSentMessages(
            @PathVariable Long userId
    ){
        List<Message> messages = messageService.getSentMessagesByUser(userId);
        List<MessageResponseDto> messagesResponseDto = messageMapper.toResponseDtoList(messages);
        return ResponseEntity.ok(messagesResponseDto);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<MessageResponseDto>> getReceivedMessages(
            @PathVariable Long userId
    ){
        List<Message> messages = messageService.getReceivedMessagesByUser(userId);
        List<MessageResponseDto> messagesResponseDto = messageMapper.toResponseDtoList(messages);
        return ResponseEntity.ok(messagesResponseDto);
    }

    @GetMapping("/conversation/{userId1}/{userId2}")
    public ResponseEntity<List<MessageResponseDto>> getConversationBetweenUsers(
            @PathVariable Long userId1, @PathVariable Long userId2
    ){
        List<Message> messages = messageService.getConversationBetweenUsers(userId1, userId2);
        List<MessageResponseDto> messagesResponseDto = messageMapper.toResponseDtoList(messages);
        return ResponseEntity.ok(messagesResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDto> updateMessage(
            @PathVariable Long id, String content
    ){
        Message message = messageService.updateMessage(id, content);
        MessageResponseDto messageResponseDto = messageMapper.toResponseDto(message);
        return ResponseEntity.ok(messageResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(
            @PathVariable Long id
    ){
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
