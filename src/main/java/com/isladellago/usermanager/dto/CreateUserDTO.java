package com.isladellago.usermanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {

    private final String email;
    private final String password;
    private final String fullName;
}
