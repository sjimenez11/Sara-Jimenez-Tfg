package com.connectedReads.dtos.User;

import com.connectedReads.entities.ReadingList;
import com.connectedReads.entities.enums.Role;
import jakarta.annotation.Nullable;
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
}
