package com.connectedReads.dtos.User;

import com.connectedReads.entities.BookForSale;
import com.connectedReads.entities.Message;
import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.Review;
import com.connectedReads.entities.enums.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private Role role;
    private Set<ReadingList> readingLists;
    private Set<Review> reviews;
    private Set<Message> sentMessages;
    private Set<Message> receivedMessages;
    private Set<BookForSale> booksForSale;
}
