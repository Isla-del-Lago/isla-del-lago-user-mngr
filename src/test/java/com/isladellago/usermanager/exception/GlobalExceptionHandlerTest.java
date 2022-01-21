package com.isladellago.usermanager.exception;

import com.isladellago.usermanager.domain.dto.ErrorResponseDTO;
import com.isladellago.usermanager.domain.enums.ErrorCodeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

    private static final String USER_EMAIL = "admin@gmail.com";
    private static final String USER_FULL_NAME = "Juanito Example";

    private final GlobalExceptionHandler globalExceptionHandler =
            new GlobalExceptionHandler();

    @Test
    public final void testHandleUserNotFound() {
        final UserNotFoundException userNotFoundException =
                new UserNotFoundException(USER_EMAIL);

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleUserNotFound(userNotFoundException);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L102.getErrorMessage(), response.getBody().getError()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L102.getErrorCode(), response.getBody().getErrorCode()
        );
    }

    @Test
    public final void testHandleUserAlreadyCreated() {
        final UserAlreadyCreatedException userAlreadyCreatedException =
                new UserAlreadyCreatedException(USER_EMAIL, USER_FULL_NAME);

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleUserAlreadyCreated(userAlreadyCreatedException);

        Assert.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L101.getErrorCode(), response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L101.getErrorMessage(), response.getBody().getError()
        );
    }

    @Test
    public final void testHandleErrorCreatingUser() {
        final ErrorCreatingUserException errorCreatingUserException =
                new ErrorCreatingUserException(USER_EMAIL);

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleErrorCreatingUser(errorCreatingUserException);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L100.getErrorCode(), response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L100.getErrorMessage(), response.getBody().getError()
        );
    }

    @Test
    public final void testHandleBadCredentials() {
        final BadCredentialsException badCredentialsException =
                new BadCredentialsException(USER_EMAIL);

        final ResponseEntity<ErrorResponseDTO> response =
                globalExceptionHandler.handleBadCredentials(badCredentialsException);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(
                ErrorCodeEnum.L001.getErrorCode(), response.getBody().getErrorCode()
        );
        Assert.assertEquals(
                ErrorCodeEnum.L001.getErrorMessage(), response.getBody().getError()
        );
    }

}
