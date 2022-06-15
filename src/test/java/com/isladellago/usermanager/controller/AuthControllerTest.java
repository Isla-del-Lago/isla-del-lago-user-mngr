package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.domain.dto.SuccessfulLoginDTO;
import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.exception.BadCredentialsException;
import com.isladellago.usermanager.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class AuthControllerTest {

    private static final String LOGIN_EMAIL = "admin@gmail.com";
    private static final String LOGIN_PASSWORD = "Pruebas1234";
    private static final String USER_FULL_NAME = "Johan Camilo Lopez Giron";
    private static final String TOKEN = UUID.randomUUID().toString();
    private static final int USER_ID = 1;

    private UserLoginDTO userLoginDTO;
    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public final void initFields() {
        userLoginDTO = UserLoginDTO.builder()
                .email(LOGIN_EMAIL)
                .password(LOGIN_PASSWORD)
                .build();

        user = User.builder()
                .email(LOGIN_EMAIL)
                .fullName(USER_FULL_NAME)
                .id(USER_ID)
                .password(LOGIN_PASSWORD)
                .build();
    }

    @Test
    public final void loginTest() {
        Mockito.when(userService.hasValidCredentials(userLoginDTO))
                .thenReturn(true);
        Mockito.when(userService.getUserByEmail(LOGIN_EMAIL))
                .thenReturn(user);

        final SuccessfulLoginDTO response = authController.login(userLoginDTO);
        
        Assert.assertEquals(TOKEN, response.getToken());
    }

    @Test(expected = BadCredentialsException.class)
    public final void loginFailsTest() {
        Mockito.when(userService.hasValidCredentials(userLoginDTO))
                .thenReturn(false);

        authController.login(userLoginDTO);
    }
}
