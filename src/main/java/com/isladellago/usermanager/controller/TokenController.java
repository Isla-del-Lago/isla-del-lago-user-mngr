package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.dto.TokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping
    public final ResponseEntity<TokenDto> generateToken() {

    }
}
