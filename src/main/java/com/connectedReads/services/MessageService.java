package com.connectedReads.services;

import com.connectedReads.dtos.MessageRequestDto;
import com.connectedReads.entities.Message;
import com.connectedReads.entities.User;
import com.connectedReads.exceptions.ConnectedReadsException;
import com.connectedReads.repositories.MessageRepository;
import com.connectedReads.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    public Message createMessage(MessageRequestDto messageRequestDto){
        User sender = userRepository.findById(messageRequestDto.getSenderId())
                .orElseThrow(() -> new ConnectedReadsException("User with ID " + messageRequestDto.getSenderId() + " not found"));

        User recipient = userRepository.findById(messageRequestDto.getRecipientId())
                .orElseThrow(() -> new ConnectedReadsException("User with ID " + messageRequestDto.getRecipientId() + " not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(messageRequestDto.getContent());
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getSentMessagesByUser(Long userId){
        return messageRepository.findAllBySenderId(userId);
    }

    public List<Message> getReceivedMessagesByUser(Long userId){
        return messageRepository.findAllByRecipientId(userId);
    }

    public List<Message> getConversationBetweenUsers(Long userId1, Long userId2){
        // Busca todas las conversaciones donde userId1 envió mensajes a userId2 o viceversa
        List<Message> conversation1 = messageRepository.findAllBySenderIdAndRecipientIdOrderByTimestamp(userId1, userId2);
        List<Message> conversation2 = messageRepository.findAllBySenderIdAndRecipientIdOrderByTimestamp(userId2, userId1);

        // Combina ambas listas de conversaciones
        List<Message> combinedConversation = new ArrayList<>();
        combinedConversation.addAll(conversation1);
        combinedConversation.addAll(conversation2);

        // Ordena la conversación combinada por timestamp
        combinedConversation.sort(Comparator.comparing(Message::getTimestamp));

        return combinedConversation;
    }

    public Message updateMessage(Long id, String content){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ConnectedReadsException("Message with ID " + id + " not found"));

        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public void deleteMessage(Long id){
        if(!messageRepository.existsById(id)) throw new ConnectedReadsException("Message with ID " + id + " not found");
        messageRepository.deleteById(id);
    }
}
