package com.isladellago.usermanager.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ErrorResponseDTO {

    private String error;
    private String errorCode;
}
