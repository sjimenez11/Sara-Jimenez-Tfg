package com.connectedReads.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipientId", nullable = false)
    private User recipient;
    @Column(nullable = false)
    private String content;
    private LocalDateTime timestamp;
}
