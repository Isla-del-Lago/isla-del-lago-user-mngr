package com.isladellago.usermanager.dto;

import com.sun.istack.NotNull;
import lombok.Data;

/**
 * This class maps the fields that the user enter to login
 * into the application.
 */
@Data
public final class UserLoginDTO {

    @NotNull
    private final String email;

    @NotNull
    private final String password;
}
