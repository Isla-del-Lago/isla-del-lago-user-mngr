package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.dto.CreateUserResponseDTO;
import com.isladellago.usermanager.model.User;
import com.isladellago.usermanager.service.UserService;
import com.isladellago.usermanager.util.CustomHttpHeaders;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
@Log4j2
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/health")
    public final ResponseEntity<String> health() {
        return ResponseEntity
                .ok("Service is healthy");
    }

    @PostMapping("/create")
    public final ResponseEntity<CreateUserResponseDTO> create(@RequestBody User user) {
        log.info("[Create user] Request received, user email: {}", user.getEmail());

        final Integer userId = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CreateUserResponseDTO.builder()
                                .userId(userId)
                                .build()
                );
    }

    @GetMapping
    public final ResponseEntity<List<User>> getAllUsers(
            @RequestHeader(CustomHttpHeaders.UUID_HEADER) UUID uuid,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        log.info("[Get all users] Request received, uuid: {}, token: {}", uuid, authToken);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

}
