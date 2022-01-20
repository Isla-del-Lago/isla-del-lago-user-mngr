package com.isladellago.usermanager.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.service.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("auth0TokenService")
@Log4j2
public class Auth0TokenServiceImpl implements TokenService {

    private String keySecret;

    @Override
    public String generateToken(User user) {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(keySecret);
            final String token = JWT.create()
                    .sign(algorithm);

            log.info("[Generate token] Generated token: {}", token);

            return token;
        } catch (JWTCreationException exception) {
            log.error("[Generate token] Error creating the token");

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean validate(String token) {
        return false;
    }

    @Override
    public String getEmailFromToken(String token) {
        return "prueba@isladellago.com";
    }

    @Value("${jwt.signature.secret}")
    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }
}
