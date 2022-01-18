package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.model.User;
import io.jsonwebtoken.lang.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class Auth0TokenServiceImplTest {

    private User user;

    @InjectMocks
    private Auth0TokenServiceImpl auth0TokenService;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public final void initPropertyValues() {
        auth0TokenService.setKeySecret(UUID.randomUUID().toString());
    }

    @Before
    public final void initFields() {
        user = User.builder().build();
    }

    @Test
    public final void testGenerateToken() {
        final String token = auth0TokenService.generateToken(user);

        Assert.notNull(token);
    }
}
