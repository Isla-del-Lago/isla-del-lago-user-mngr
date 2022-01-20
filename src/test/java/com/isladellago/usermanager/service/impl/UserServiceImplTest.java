package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.domain.model.UserRepository;
import com.isladellago.usermanager.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class UserServiceImplTest {

    private static final String FULL_NAME = "Usuario De Prueba";
    private static final String EMAIL = "admin@isladellago.com";
    private static final int USER_ID = 1;

    private User user;

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
        Assert.assertFalse(user.isAccountNonExpired());
        Assert.assertFalse(user.isAccountNonLocked());
        Assert.assertFalse(user.isCredentialsNonExpired());
        Assert.assertEquals(0, user.getAuthorities().size());
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
}
