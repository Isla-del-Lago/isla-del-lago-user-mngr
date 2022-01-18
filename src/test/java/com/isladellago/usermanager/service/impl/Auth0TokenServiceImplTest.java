package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class Auth0TokenServiceImplTest {

    private static final String TOKEN = UUID.randomUUID().toString();

    private User user;

    private Auth0TokenServiceImpl auth0TokenService = new Auth0TokenServiceImpl();

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

        Assert.assertNotNull(token);
    }

    @Test
    public final void testValidateIsFalse() {
        Assert.assertFalse(auth0TokenService.validate(TOKEN));
    }

    @Test
    public final void testGetEmailFromToken() {
        Assert.assertEquals("prueba@isladellago.com", auth0TokenService.getEmailFromToken(TOKEN));
    }
}
