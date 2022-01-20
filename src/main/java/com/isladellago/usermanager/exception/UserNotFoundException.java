package com.isladellago.usermanager.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public final class UserNotFoundException extends RuntimeException {

    private int userId;
    private String email;

    public UserNotFoundException(String email) {
        super();
        this.email = email;
    }

    public UserNotFoundException(int userId, String email) {
        super();
        this.userId = userId;
        this.email = email;
    }

}
