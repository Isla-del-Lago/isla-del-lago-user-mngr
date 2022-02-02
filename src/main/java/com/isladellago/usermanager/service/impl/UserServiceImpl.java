package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.domain.dto.CreateUserDTO;
import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.domain.model.Apartment;
import com.isladellago.usermanager.domain.model.ApartmentRepository;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.domain.model.UserRepository;
import com.isladellago.usermanager.exception.*;
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
    private final ApartmentRepository apartmentRepository;

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

    @Override
    public User mapUserFromCreateUserDTO(CreateUserDTO createUserDTO) {
        log.info("[Map user entity from create user dto] Method start, user dto: {}",
                createUserDTO.toString());

        if (!apartmentRepository.existsById(createUserDTO.getApartmentId())) {
            throw new ApartmentNotFoundException(createUserDTO.getApartmentId());
        }

        final Apartment apartment =
                apartmentRepository.getById(createUserDTO.getApartmentId());

        return User.builder()
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .fullName(createUserDTO.getFullName())
                .apartment(apartment)
                .build();
    }
}
