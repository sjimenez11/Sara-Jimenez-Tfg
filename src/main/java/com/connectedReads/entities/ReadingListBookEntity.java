package com.connectedReads.entities;

import com.connectedReads.entities.enums.ReadingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data // getters, setters, métodos toString(), equals(), y hashCode()
@Table(name = "readingListBooks")
public class ReadingListBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ReadingListEntity readingList;
    @ManyToOne
    private BookEntity book;
    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

}
