package com.isladellago.usermanager.exception;

import com.isladellago.usermanager.domain.dto.ErrorResponseDTO;
import com.isladellago.usermanager.domain.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handleUserNotFound(
            UserNotFoundException ex) {
        log.error("User with email: {} not found", ex.getEmail());

        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(ErrorCodeEnum.L102.getErrorMessage())
                .errorCode(ErrorCodeEnum.L102.getErrorCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(UserAlreadyCreatedException.class)
    public final ResponseEntity<ErrorResponseDTO> handleUserAlreadyCreated(
            UserAlreadyCreatedException ex) {
        log.error("User with email: {} or full name: {} is already created",
                ex.getEmail(), ex.getFullName());

        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(ErrorCodeEnum.L101.getErrorMessage())
                .errorCode(ErrorCodeEnum.L101.getErrorCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(ErrorCreatingUserException.class)
    public final ResponseEntity<ErrorResponseDTO> handleErrorCreatingUser(
            ErrorCreatingUserException ex) {
        log.error("Error creating user with email: {}", ex.getEmail());

        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(ErrorCodeEnum.L100.getErrorMessage())
                .errorCode(ErrorCodeEnum.L100.getErrorCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBadCredentials(
            BadCredentialsException ex) {
        log.error("Bad credentials for user email: {}", ex.getEmail());

        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(ErrorCodeEnum.L001.getErrorMessage())
                .errorCode(ErrorCodeEnum.L001.getErrorCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }
}
