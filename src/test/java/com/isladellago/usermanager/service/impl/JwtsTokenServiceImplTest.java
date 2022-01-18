package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

public class JwtsTokenServiceImplTest {

    private static final String TOKEN = UUID.randomUUID().toString();
    private static final String FULL_NAME = "Usuario De Prueba";
    private static final String EMAIL = "admin@isladellago.com";
    private static final int USER_ID = 1;

    private User user;

    private JwtsTokenServiceImpl jwtsTokenService = new JwtsTokenServiceImpl();

    @Before
    public final void setPropertyValues() {
        jwtsTokenService.setJwtSignatureSecret(UUID.randomUUID().toString());
    }

    @Before
    public final void initFields() {
        user = User.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .id(USER_ID)
                .build();
    }

    @Test
    public final void testGenerateToken() {
        final String token = jwtsTokenService.generateToken(user);

        Assert.assertNotNull(token);
        ObjectUtils.isEmpty(token);
    }

    @Test
    public final void testValidate() {
        final String token = jwtsTokenService.generateToken(user);
        Assert.assertTrue(jwtsTokenService.validate(token));
    }

    @Test
    public final void testValidateFailsSignature() {
        jwtsTokenService.setJwtSignatureSecret("");
        Assert.assertFalse(jwtsTokenService.validate(TOKEN));
    }

    @Test
    public final void testValidateFailsMalformedJwt() {
        Assert.assertFalse(jwtsTokenService.validate(TOKEN + UUID.randomUUID().toString()));
    }

    @Test
    public final void testValidateFailsIllegalArgument() {
        Assert.assertFalse(jwtsTokenService.validate(null));
    }

    @Test
    public final void testGetEmailFromToken() {
        final String token = jwtsTokenService.generateToken(user);
        final String email = jwtsTokenService.getEmailFromToken(token);

        Assert.assertEquals(EMAIL, email);
    }
}
