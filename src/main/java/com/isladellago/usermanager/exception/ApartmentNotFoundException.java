package com.isladellago.usermanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApartmentNotFoundException extends RuntimeException {

    private String apartmentId;
}
