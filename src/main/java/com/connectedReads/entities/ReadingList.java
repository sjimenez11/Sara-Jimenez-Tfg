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
@Table(name = "readingLists")
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String name;
    private String description;
    @OneToMany(mappedBy = "readingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReadingListBook> readingListBooks;
}

