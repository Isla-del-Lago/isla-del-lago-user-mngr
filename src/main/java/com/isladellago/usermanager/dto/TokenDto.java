package com.isladellago.usermanager.dto;

/**
 * This class maps the generate token response
 */
public final class TokenDto {

    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
