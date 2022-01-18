package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.dto.CreateUserResponseDTO;
import com.isladellago.usermanager.model.User;
import com.isladellago.usermanager.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class UserControllerTest {

    private static final String LOGIN_EMAIL = "admin@gmail.com";
    private static final String LOGIN_PASSWORD = "Pruebas1234";
    private static final String USER_FULL_NAME = "Johan Camilo Lopez Giron";
    private static final String TOKEN = UUID.randomUUID().toString();
    private static final int USER_ID = 1;

    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public final void initFields() {
        user = User.builder()
                .email(LOGIN_EMAIL)
                .fullName(USER_FULL_NAME)
                .id(USER_ID)
                .password(LOGIN_PASSWORD)
                .build();
    }

    @Test
    public final void createUserTest() {
        Mockito.when(userService.createUser(Mockito.any()))
                .thenReturn(USER_ID);

        final ResponseEntity<CreateUserResponseDTO> response =
                userController.create(user);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals((int) response.getBody().getUserId(), USER_ID);
    }

    @Test
    public final void testGetAllUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of());

        final ResponseEntity<List<User>> response =
                userController.getAllUsers(UUID.randomUUID(), UUID.randomUUID().toString());

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(0, response.getBody().size());
    }
}
