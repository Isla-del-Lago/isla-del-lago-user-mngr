package com.isladellago.usermanager.domain.enums;

public enum ErrorCodeEnum {

    L000("L-000", "Internal server error"),
    L001("L-001", "Email or password incorrect"),
    L100("L-100", "Error creating user, please try again"),
    L101("L-101", "There is an user created with this email or full name"),
    L102("L-102", "User not found"),
    L200("L-200", "Apartment not found");

    private final String errorMessage;
    private final String errorCode;

    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
