package com.isladellago.usermanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class ErrorCreatingUserException extends RuntimeException {

    private final String email;
}
