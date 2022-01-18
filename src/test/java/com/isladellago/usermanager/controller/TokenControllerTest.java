package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.dto.SuccessfulLoginDTO;
import com.isladellago.usermanager.dto.UserLoginDTO;
import com.isladellago.usermanager.model.User;
import com.isladellago.usermanager.service.TokenService;
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

import java.util.UUID;

public class TokenControllerTest {

    private static final String LOGIN_EMAIL = "admin@gmail.com";
    private static final String LOGIN_PASSWORD = "Pruebas1234";
    private static final String USER_FULL_NAME = "Johan Camilo Lopez Giron";
    private static final String TOKEN = UUID.randomUUID().toString();
    private static final int USER_ID = 1;

    private UserLoginDTO userLoginDTO;
    private User user;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TokenController tokenController;

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
        Mockito.when(userService.getUserByEmail(LOGIN_EMAIL))
                .thenReturn(user);

        Mockito.when(tokenService.generateToken(Mockito.any()))
                .thenReturn(TOKEN);

        final ResponseEntity<SuccessfulLoginDTO> response =
                tokenController.login(userLoginDTO);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getToken(), TOKEN);
    }
}
