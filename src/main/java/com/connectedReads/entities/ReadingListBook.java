package com.connectedReads.entities;

import com.connectedReads.entities.enums.ReadingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "readingListBook")
public class ReadingListBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idBook", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "idReadingList", nullable = false)
    private ReadingList readingList;
    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

}
