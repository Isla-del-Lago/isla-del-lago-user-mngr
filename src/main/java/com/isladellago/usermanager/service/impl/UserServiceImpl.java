package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.domain.model.UserRepository;
import com.isladellago.usermanager.exception.UserAlreadyCreatedException;
import com.isladellago.usermanager.exception.UserNotFoundException;
import com.isladellago.usermanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Integer createUser(User user) {
        log.info("[Create user] Method start, user: {}", user);
        try {
            return userRepository
                    .save(user)
                    .getId();
        } catch (Exception ex) {
            throw new UserAlreadyCreatedException(user.getEmail());
        }
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("[Get user by email] Method start, email: {}", email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public List<User> getAllUsers() {
        log.info("[Get all users] Method start");
        return userRepository.findAll();
    }

    @Override
    public void deleteUserByEmail(String userEmail) {
        log.info("[Delete user by email] Method start, user email: {}", userEmail);

        userRepository.deleteByEmail(userEmail);
    }
}
