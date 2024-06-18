package com.connectedReads.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data // getters, setters, métodos toString(), equals(), y hashCode()
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String synopsis;
    @OneToMany(mappedBy = "book")
    private Set<ReadingListBook> readingListBooks;
    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;
}
