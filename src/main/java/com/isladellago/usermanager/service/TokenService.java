package com.isladellago.usermanager.service;

import com.isladellago.usermanager.dto.TokenDto;

/**
 * Token functionalities
 */
public interface TokenService {

    /**
     * This class generates a new token.
     *
     * @return Generated token.
     */
    TokenDto generateToken();
}
