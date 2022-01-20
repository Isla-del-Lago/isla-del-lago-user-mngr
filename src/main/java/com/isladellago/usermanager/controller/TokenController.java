package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.domain.dto.SuccessfulLoginDTO;
import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.service.TokenService;
import com.isladellago.usermanager.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * This class hold all token related functionalities
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*") //NOSONAR
@Log4j2
public class TokenController {

    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public TokenController(
            @Qualifier("jwtsTokenService") TokenService tokenService,
            UserService userService) {

        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessfulLoginDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("[Login] Request received, user email: {}",
                userLoginDTO.getEmail());

        final User user = userService.getUserByEmail(userLoginDTO.getEmail());

        final String token = tokenService.generateToken(user);
        final UUID uuid = UUID.randomUUID();

        final SuccessfulLoginDTO loginResponse = SuccessfulLoginDTO.builder()
                .uuid(uuid)
                .token(token)
                .build();

        log.info("[Login] Generated uuid and token: {}", loginResponse.toString());

        return ResponseEntity.ok(loginResponse);
    }
}
