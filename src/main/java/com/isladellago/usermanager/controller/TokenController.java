package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.dto.TokenDto;
import com.isladellago.usermanager.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class hold all token related functionalities
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class TokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    private TokenService tokenService;

    @PostMapping("/token")
    public final ResponseEntity<TokenDto> generateToken() {
        LOGGER.info("[GENERATE TOKEN CONTROLLER] REQUEST RECEIVED");

        final TokenDto tokenDto = tokenService.generateToken();

        return new ResponseEntity<>(tokenDto, HttpStatus.CREATED);
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
