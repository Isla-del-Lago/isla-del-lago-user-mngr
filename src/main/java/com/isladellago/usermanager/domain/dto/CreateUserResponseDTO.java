package com.isladellago.usermanager.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class CreateUserResponseDTO {

    private final Integer userId;
    private final String message = "User created";
}
