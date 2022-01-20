package com.isladellago.usermanager.domain.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * This class maps the fields that the user enter to login
 * into the application.
 */
@Getter
@Builder
public final class UserLoginDTO {

    private final String email;
    private final String password;
}
