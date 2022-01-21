package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.domain.model.UserRepository;
import com.isladellago.usermanager.exception.BadCredentialsException;
import com.isladellago.usermanager.exception.ErrorCreatingUserException;
import com.isladellago.usermanager.exception.UserAlreadyCreatedException;
import com.isladellago.usermanager.exception.UserNotFoundException;
import com.isladellago.usermanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        } catch (DataIntegrityViolationException ex) {
            log.error(ex.getMessage());
            throw new UserAlreadyCreatedException(
                    user.getEmail(), user.getFullName()
            );
        } catch (Exception ex) {
            log.error(ex);
            throw new ErrorCreatingUserException(user.getEmail());
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
    @Transactional
    public void deleteUserByEmail(String userEmail) {
        log.info("[Delete user by email] Method start, user email: {}", userEmail);

        userRepository.deleteByEmail(userEmail);
    }

    @Override
    public boolean hasValidCredentials(UserLoginDTO userLoginDTO) {
        log.info("[Has valid credentials] Method start, user email: {}",
                userLoginDTO.getEmail());

        final User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException(userLoginDTO.getEmail()));

        return userLoginDTO.getPassword().equals(user.getPassword());
    }
}
