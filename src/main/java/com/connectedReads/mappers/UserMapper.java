package com.connectedReads.mappers;

import com.connectedReads.dtos.User.UserDto;
import com.connectedReads.dtos.User.UserDtoRegister;
import com.connectedReads.entities.User;
import com.connectedReads.entities.enums.Role;
import com.connectedReads.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserMapper {
    public User fromDtoRegister(UserDtoRegister dto, UserRepository repository){
        Optional<User> user = repository.findByUserName(dto.getEmail());
        if(user.isPresent() || !Objects.equals(dto.getPassword(), dto.getRepeatPassword())){
            return null;
        }else return new User(null, dto.getEmail(), dto.getPassword(), Role.USER, null);
    }

    public UserDto toDto(User entity){
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getRole(),
                entity.getReadingLists()
        );
    }

    public List<UserDto> toDto(List<User> entities){
        return entities.stream().map(this::toDto).toList();
    }
}
