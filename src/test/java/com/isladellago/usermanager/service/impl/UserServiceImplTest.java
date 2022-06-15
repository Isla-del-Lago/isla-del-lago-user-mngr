package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.domain.model.UserRepository;
import com.isladellago.usermanager.exception.BadCredentialsException;
import com.isladellago.usermanager.exception.ErrorCreatingUserException;
import com.isladellago.usermanager.exception.UserAlreadyCreatedException;
import com.isladellago.usermanager.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImplTest {

    private static final String FULL_NAME = "Usuario De Prueba";
    private static final String EMAIL = "admin@isladellago.com";
    private static final String USER_PASSWORD = UUID.randomUUID().toString();
    private static final int USER_ID = 1;

    private User user;
    private UserLoginDTO userLoginDTO, userLoginDTO_1;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public final void initFields() {
        user = User.builder()
                .id(USER_ID)
                .fullName(FULL_NAME)
                .email(EMAIL)
                .password(USER_PASSWORD)
                .build();

        userLoginDTO = UserLoginDTO.builder()
                .email(EMAIL)
                .password(USER_PASSWORD)
                .build();

        userLoginDTO_1 = UserLoginDTO.builder()
                .email(EMAIL)
                .password(USER_PASSWORD + "x")
                .build();
    }

    @Test
    public final void testCreateUser() {
        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(user);

        final int userId = userService.createUser(user);

        Assert.assertEquals(USER_ID, userId);
    }

    @Test
    public final void testGetUserByEmail() {
        Mockito.when(userRepository.findByEmail(EMAIL))
                .thenReturn(Optional.of(user));

        final User userFromEmail = userService.getUserByEmail(EMAIL);

        Assert.assertNotNull(userFromEmail);
        Assert.assertEquals(user, userFromEmail);
        Assert.assertEquals(EMAIL, user.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public final void testGetUserByEmailDoesNotExists() {
        Mockito.when(userRepository.findByEmail(EMAIL))
                .thenReturn(Optional.empty());

        userService.getUserByEmail(EMAIL);
    }

    @Test
    public final void testGetAllUsers() {
        Mockito.when(userRepository.findAll())
                .thenReturn(List.of());

        final List<User> users = userService.getAllUsers();

        Assert.assertNotNull(users);
        Assert.assertEquals(0, users.size());
    }

    @Test(expected = UserAlreadyCreatedException.class)
    public final void testCreateUserIsCreated() {
        Mockito.when(userRepository.save(user))
                .thenThrow(new DataIntegrityViolationException(""));
        userService.createUser(user);
    }

    @Test(expected = ErrorCreatingUserException.class)
    public final void testErrorCreatingUser() {
        Mockito.when(userRepository.save(user))
                .thenThrow(new NullPointerException());
        userService.createUser(user);
    }

    @Test
    public final void testDeleteUserByEmail() {
        Mockito.doNothing().when(userRepository).deleteByEmail(EMAIL);

        userService.deleteUserByEmail(EMAIL);

        Mockito.verify(userRepository).deleteByEmail(EMAIL);
    }

    @Test
    public final void testHasValidCredentials() {
        Mockito.when(userRepository.findByEmail(userLoginDTO.getEmail()))
                .thenReturn(Optional.of(user));

        final boolean hasValidCredentials =
                userService.hasValidCredentials(userLoginDTO);

        Assert.assertTrue(hasValidCredentials);
    }

    @Test
    public final void testHasNotValidCredentials() {
        Mockito.when(userRepository.findByEmail(userLoginDTO_1.getEmail()))
                .thenReturn(Optional.of(user));

        final boolean hasValidCredentials =
                userService.hasValidCredentials(userLoginDTO_1);

        Assert.assertFalse(hasValidCredentials);
    }

    @Test(expected = BadCredentialsException.class)
    public final void testHasNotValidCredentials_UserDoesNotExists() {
        Mockito.when(userRepository.findByEmail(userLoginDTO.getEmail()))
                .thenReturn(Optional.empty());

        userService.hasValidCredentials(userLoginDTO);

    }
}
