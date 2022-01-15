package com.isladellago.usermanager.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public final class SuccessfulLoginDTO {

    @NotNull
    private final UUID uuid;

    @NotNull
    private final String token;
}
