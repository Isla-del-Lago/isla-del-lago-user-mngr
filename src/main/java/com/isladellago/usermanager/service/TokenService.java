package com.isladellago.usermanager.service;

import com.isladellago.usermanager.model.User;

/**
 * Token functionalities
 */
public interface TokenService {

    /**
     * This class generates a new token.
     *
     * @param user User login details.
     * @return Generated token.
     */
    String generateToken(User user);

    /**
     * This method validates the given token.
     *
     * @param token Token to be validated.
     * @return If the token is valid.
     */
    boolean validate(String token);

    /**
     * Get the email from the given token.
     *
     * @param token Given validated token.
     * @return Email from the token payload.
     */
    String getEmailFromToken(String token);
}
