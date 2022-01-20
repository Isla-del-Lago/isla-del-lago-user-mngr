package com.isladellago.usermanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class UserAlreadyCreatedException extends RuntimeException {

    private String email;
}
