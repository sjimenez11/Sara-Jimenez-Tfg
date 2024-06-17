package com.connectedReads.controllers;

import com.connectedReads.config.jwt.JwtTokenUtils;
import com.connectedReads.dtos.User.*;
import com.connectedReads.entities.User;
import com.connectedReads.entities.enums.Role;
import com.connectedReads.exceptions.UserException;
import com.connectedReads.mappers.UserMapper;
import com.connectedReads.services.UserService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, UserMapper userMapper){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> findAll(
            @AuthenticationPrincipal User user,
            @RequestParam @Nullable String role
    ){
        try{
            if(role != null && !role.isEmpty()){
                //Find users by role
                return ResponseEntity.ok(userService.findUsersByRole(Role.valueOf(role)));
            }
            //Find all users
            return ResponseEntity.ok(userService.findAll());
        }catch (Exception e){
            throw new UserException.UserBadRequestException(e.getMessage());
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> findByEmail(
            @AuthenticationPrincipal User user,
            @PathVariable String email
    ){
        log.info("find by email " + email);
        return
                ResponseEntity.ok(userService.findUserByEmail(email));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ){
        log.info("find by id " + id);
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping("/create")
    public ResponseEntity<UserDtoWithToken> create(
            @AuthenticationPrincipal User user,
            @Validated @RequestBody UserDtoCreate dto
    ){
        return
                ResponseEntity.created(null).body(userService.create(dto));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDtoWithToken> register(
            @RequestBody UserDtoRegister dto
    ){
        return
                ResponseEntity.created(null).body(userService.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDtoWithToken> login(
            @Validated @RequestBody UserDtoLogin dto
    ){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getEmail(),
                                dto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication
        );
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new
                UserDtoWithToken(userMapper.toDto(user),
                jwtTokenUtils.create(user)));
    }
    @PutMapping("/update/me")
    public ResponseEntity<UserDto> updateMySelf(
            @AuthenticationPrincipal User user,
            @Validated @RequestBody UserDtoUpdate dto
    ){
        log.info("Updating self");
        return
                ResponseEntity.ok(userService.updateSelf(user.getId(), dto));
    }
    @PutMapping("/update")
    public ResponseEntity<UserDto> update(
            @AuthenticationPrincipal User user,
            @Validated @RequestBody UserDtoUpdate dto
    ){
        log.info("Updating");
        return ResponseEntity.ok(userService.update(dto));
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<UserDto> delete(
            @PathVariable String email,
            @AuthenticationPrincipal User user
    ){
        log.info("Deleting");
        return ResponseEntity.ok(userService.delete(email));
    }
}
