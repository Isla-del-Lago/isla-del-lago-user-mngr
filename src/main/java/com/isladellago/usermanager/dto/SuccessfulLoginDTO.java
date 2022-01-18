package com.isladellago.usermanager.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public final class SuccessfulLoginDTO {

    private final UUID uuid;
    private final String token;
}
