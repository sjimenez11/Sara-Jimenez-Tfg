package com.connectedReads.dtos.User;

import com.connectedReads.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoCreate {
    private String email;
    private String password;
    private Role role;
}
