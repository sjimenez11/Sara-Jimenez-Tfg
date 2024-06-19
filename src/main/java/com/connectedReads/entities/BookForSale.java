package com.connectedReads.entities;

import com.connectedReads.entities.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "bookForSale")
public class BookForSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;
    @Column(nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private String description;
}
