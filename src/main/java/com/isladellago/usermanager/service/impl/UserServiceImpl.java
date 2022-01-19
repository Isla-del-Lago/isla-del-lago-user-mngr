package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.model.User;
import com.isladellago.usermanager.model.UserRepository;
import com.isladellago.usermanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Integer createUser(User user) {
        log.info("[Create user] Method start, user: {}", user);
        return userRepository
                .save(user)
                .getId();
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("[Get user by email] Method start, email: {}", email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> {
                            log.error("[Get user by email] User with email: {} not found", email);
                            return new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    String.format("User with email: %s not found", email));
                        }
                );
    }

    @Override
    public List<User> getAllUsers() {
        log.info("[Get all users] Method start");
        return userRepository.findAll();
    }
}
|