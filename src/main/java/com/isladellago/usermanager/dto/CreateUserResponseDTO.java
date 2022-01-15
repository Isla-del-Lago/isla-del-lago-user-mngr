package com.isladellago.usermanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CreateUserResponseDTO {

    private Integer userId;
}
