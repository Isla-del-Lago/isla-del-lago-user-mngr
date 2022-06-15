package com.isladellago.usermanager.controller;

import com.isladellago.usermanager.domain.dto.SuccessfulLoginDTO;
import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.service.AuthService;
import com.isladellago.usermanager.util.PathUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This class hold all token related functionalities
 */
@RestController
@RequestMapping(PathUtils.Token.ROOT_PATH)
@CrossOrigin("*") //NOSONAR
@Log4j2
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(PathUtils.Token.LOGIN)
    @ResponseStatus(HttpStatus.OK)
    public SuccessfulLoginDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("[Login controller] User email: {}", userLoginDTO.getEmail());

        return authService.login(userLoginDTO);
    }
}
