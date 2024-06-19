package com.connectedReads.repositories;

import com.connectedReads.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderId(Long senderId);
    List<Message> findAllByRecipientId(Long recipientId);
    List<Message> findAllBySenderIdAndRecipientIdOrderByTimestamp(Long userId1, Long userId2);
}
