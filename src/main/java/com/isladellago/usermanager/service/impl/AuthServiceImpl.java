package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.domain.dto.CreateTokenReq;
import com.isladellago.usermanager.domain.dto.SuccessfulLoginDTO;
import com.isladellago.usermanager.domain.dto.UserLoginDTO;
import com.isladellago.usermanager.domain.model.User;
import com.isladellago.usermanager.exception.BadCredentialsException;
import com.isladellago.usermanager.service.AuthService;
import com.isladellago.usermanager.service.UserService;
import com.isladellago.usermanager.service.client.SecurityManagerClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private SecurityManagerClient securityManagerClient;

    @Override
    public SuccessfulLoginDTO login(UserLoginDTO userLoginDTO) {
        log.info("[Login service] User email: {}", userLoginDTO.getEmail());

        if (!userService.hasValidCredentials(userLoginDTO)) {
            throw new BadCredentialsException(userLoginDTO.getEmail());
        }

        final User user = userService.getUserByEmail(userLoginDTO.getEmail());
        final CreateTokenReq createTokenRequest = CreateTokenReq.builder()
                .userEmail(user.getEmail())
                .userId(user.getId())
                .build();

        final String token =
                securityManagerClient.createToken(createTokenRequest).getAccessToken();
        final UUID uuid = UUID.randomUUID();

        final SuccessfulLoginDTO loginResponse = SuccessfulLoginDTO.builder()
                .uuid(uuid)
                .token(token)
                .build();

        log.info("[Login service] Generated uuid and token: {}", loginResponse.toString());

        return loginResponse;
    }
}
