package com.isladellago.usermanager.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * This class maps the fields that the user enter to login
 * into the application.
 */
@Data
@Builder
public final class UserLoginDTO {

    @NotNull
    private final String email;

    @NotNull
    private final String password;
}
