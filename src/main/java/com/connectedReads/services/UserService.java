package com.connectedReads.services;

import com.connectedReads.config.jwt.JwtTokenUtils;
import com.connectedReads.dtos.User.*;
import com.connectedReads.entities.User;
import com.connectedReads.entities.enums.Role;
import com.connectedReads.exceptions.UserException;
import com.connectedReads.mappers.UserMapper;
import com.connectedReads.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder = new
            BCryptPasswordEncoder(12); //longitud cadena codeada
    @Autowired
    public UserService(UserRepository userRepository, JwtTokenUtils
            jwtTokenUtils, UserMapper mapper){
        this.userRepository = userRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.mapper = mapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(()-> new
                        UserException.UserNotFoundException(
                        "User with email " + username + "not found."));
    }
    public UserDtoWithToken register(UserDtoRegister dto){
        if(!Objects.equals(dto.getPassword(),
                dto.getRepeatPassword())){
            throw new UserException.UserBadRequestException(
                    "Password and repeated password do not match");
        }
        Optional<com.connectedReads.entities.User> user = userRepository.findByUserName(dto.getEmail());
        if (user.isPresent()){
            throw new UserException.UserBadRequestException(
                    "There's already an account linked to this email.");
        }
        User saved = userRepository.save(new User(null, dto.getEmail(), encoder.encode(dto.getPassword()), Role.USER, null, null, null, null));
        return new UserDtoWithToken(
                mapper.toDto(saved),
                jwtTokenUtils.create(saved)
        );
    }
    public UserDtoWithToken create(UserDtoCreate dto){
        Optional<User> user = userRepository.findByUserName(dto.getEmail());
        if(user.isPresent()){
            throw new UserException.UserBadRequestException(
                    "There's already an account linked to this email");
        }
        User saved = userRepository.save(new User(null,
                dto.getEmail(), encoder.encode(dto.getPassword()),
                dto.getRole(), null, null, null, null));
        return new UserDtoWithToken(
                mapper.toDto(saved),
                jwtTokenUtils.create(saved)
        );
    }
    public List<UserDto> findAll() {
        return mapper.toDto(userRepository.findAll());
    }
    public List<UserDto> findUsersByRole(Role role) {
        return mapper.toDto(userRepository.findAllByRole(role));
    }
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByUserName(email)
                .orElseThrow(()-> new
                        UserException.UserNotFoundException("User with email " + email + " not found."));
        return mapper.toDto(user);
    }
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new UserException.UserNotFoundException("User with ID " + id + " not found."));
        return mapper.toDto(user);
    }
    public UserDto updateSelf(Long id, UserDtoUpdate dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new
                        UserException.UserNotFoundException(
                        "User with ID " + id + " not found."));
        if (!encoder.matches(dto.getPassword(), user.getUserPassword())) {
            throw new UserException.UserBadRequestException(
                    "Incorrect password.");
        }
        User saved = userRepository.save(new User(user.getId(),
                user.getUsername(),
                encoder.encode(dto.getNewPassword()),
                user.getRole(),
                user.getReadingLists(),
                user.getReviews(),
                user.getSentMessages(),
                user.getReceivedMessages()));
        return mapper.toDto(saved);
    }
    public UserDto update(UserDtoUpdate dto) {
        User user = userRepository.findByUserName(dto.getEmail())
                .orElseThrow(()-> new
                        UserException.UserNotFoundException(
                        "User with email " + dto.getEmail() + " not found."));
        if (!encoder.matches(dto.getPassword(), user.getUserPassword())) {
            throw new UserException.UserBadRequestException(
                    "Incorrect password.");
        }
        User saved = userRepository.save(new User(user.getId(),
                user.getUsername(),
                encoder.encode(dto.getNewPassword()),
                user.getRole(),
                user.getReadingLists(),
                user.getReviews(),
                user.getSentMessages(),
                user.getReceivedMessages()));
        return mapper.toDto(saved);
    }
    public UserDto delete(String email) {
        User user = userRepository.findByUserName(email)
                .orElseThrow(()-> new
                        UserException.UserNotFoundException(
                        "User with email " + email + " not found."));
                        userRepository.deleteById(user.getId());
        return mapper.toDto(user);
    }

}
