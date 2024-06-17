package com.connectedReads.dtos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoUpdate {
    private String email;
    private String password;
    private String newPassword;
}
