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
public class ReadingListBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idBook")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "idReadingList")
    private ReadingList readingList;
    //TODO quizá quitar el estado en la lista
    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

}
