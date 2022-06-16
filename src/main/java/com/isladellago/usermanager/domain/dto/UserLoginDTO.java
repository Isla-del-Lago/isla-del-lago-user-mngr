package com.isladellago.usermanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class maps the fields that the user enter to login
 * into the application.
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class UserLoginDTO {

    private String email;
    private String password;
}
