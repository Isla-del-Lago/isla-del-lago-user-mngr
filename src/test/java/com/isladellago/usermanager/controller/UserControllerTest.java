package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.domain.dto.CreateUserDTO;
import com.isladellago.usermanager.domain.dto.CreateUserResponseDTO;
import com.isladellago.usermanager.domain.model.User;
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
    private static final UUID AUTH_UUID = UUID.randomUUID();
    private static final String TOKEN = UUID.randomUUID().toString();
    private static final int USER_ID = 1;

    private CreateUserDTO createUserDTO;
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
        createUserDTO = CreateUserDTO.builder()
                .email(LOGIN_EMAIL)
                .fullName(USER_FULL_NAME)
                .password(LOGIN_PASSWORD)
                .build();

        user = User.builder()
                .email(LOGIN_EMAIL)
                .password(LOGIN_PASSWORD)
                .fullName(USER_FULL_NAME)
                .build();
    }

    @Test
    public final void createUserTest() {
        Mockito.when(userService.createUser(Mockito.any()))
                .thenReturn(USER_ID);

        final ResponseEntity<CreateUserResponseDTO> response =
                userController.create(createUserDTO);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(USER_ID, (int) response.getBody().getUserId());
    }

    @Test
    public final void testGetAllUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of());

        final ResponseEntity<List<User>> response =
                userController.getAllUsers(AUTH_UUID, TOKEN);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(0, response.getBody().size());
    }

    @Test
    public final void testDeleteUserByEmail() {
        Mockito.doNothing().when(userService).deleteUserByEmail(LOGIN_EMAIL);

        userController.deleteUserByEmail(UUID.randomUUID(), TOKEN, LOGIN_EMAIL);

        Mockito.verify(userService).deleteUserByEmail(LOGIN_EMAIL);
    }

    @Test
    public final void testGetUserByEmail() {
        Mockito.when(userService.getUserByEmail(LOGIN_EMAIL))
                .thenReturn(user);

        final ResponseEntity<User> response =
                userController.getUserByEmail(AUTH_UUID, TOKEN, LOGIN_EMAIL);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(LOGIN_EMAIL, response.getBody().getEmail());
        Assert.assertEquals(LOGIN_PASSWORD, response.getBody().getPassword());
        Assert.assertEquals(USER_FULL_NAME, response.getBody().getFullName());
    }
}
