package com.isladellago.usermanager.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.isladellago.usermanager.dto.TokenDto;
import com.isladellago.usermanager.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${jwt.signature.secret}")
    private String keySecret;

    @Override
    public TokenDto generateToken() {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(keySecret);
            final String token = JWT.create()
                    .sign(algorithm);

            LOGGER.info("[GENERATE TOKEN] GENERATED TOKEN: {}", token);

            return new TokenDto(token);
        } catch (JWTCreationException exception){
            LOGGER.error("[GENERATE TOKEN] ERROR CREATING THE TOKEN");

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
