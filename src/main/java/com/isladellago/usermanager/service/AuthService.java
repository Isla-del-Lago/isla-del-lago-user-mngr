package com.isladellago.usermanager.service;

import com.isladellago.usermanager.domain.dto.SuccessfulLoginDTO;
import com.isladellago.usermanager.domain.dto.UserLoginDTO;

public interface AuthService {

    /**
     * Validates the given user data and generates a token if
     * the user has valid credentials.
     *
     * @param userLoginDTO User credentials.
     * @return Generated token if user has valid credentials.
     */
    SuccessfulLoginDTO login(UserLoginDTO userLoginDTO);
}
