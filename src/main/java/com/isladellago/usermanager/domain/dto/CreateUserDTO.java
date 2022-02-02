package com.isladellago.usermanager.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateUserDTO {

    @NotNull
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    private final String fullName;

    @NotNull
    private final String apartmentId;
}
