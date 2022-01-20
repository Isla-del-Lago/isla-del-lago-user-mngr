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
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(UserNotFoundException ex) {
        log.error("User with email: {} not found", ex.getEmail());

        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(ErrorCodeEnum.L001.getErrorMessage())
                .errorCode(ErrorCodeEnum.L001.getErrorCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }

    @ExceptionHandler(UserAlreadyCreatedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyCreatedException(UserAlreadyCreatedException ex) {
        log.error("User with email: {} is already created", ex.getEmail());

        final ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .error(ErrorCodeEnum.L101.getErrorMessage())
                .errorCode(ErrorCodeEnum.L101.getErrorCode())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponseDTO);
    }
}
