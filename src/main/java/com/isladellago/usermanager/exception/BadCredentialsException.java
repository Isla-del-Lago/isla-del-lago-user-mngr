package com.isladellago.usermanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class BadCredentialsException extends RuntimeException {

    private final String email;
}
