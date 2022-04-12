package com.example.safetynetalert.core.presentation.exceptions;

import com.example.safetynetalert.core.domain.exceptions.GenericBadRequestException;
import com.example.safetynetalert.core.domain.exceptions.GenericNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler
        extends ResponseEntityExceptionHandler {

    // 400
    @ExceptionHandler(GenericBadRequestException.class)
    public ResponseEntity<HttpErrorMessage> responseExceptionsHandler(
            GenericBadRequestException exception,
            WebRequest request) {
        return new HttpErrorMessage(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getDescription(false))
                .toResponseEntity();
    }

    // 404
    @ExceptionHandler(GenericNotFoundException.class)
    public ResponseEntity<HttpErrorMessage> responseExceptionsHandler(
            GenericNotFoundException exception,
            WebRequest request) {

        return new HttpErrorMessage(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false))
                .toResponseEntity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorMessage> handleGeneralException(
            Exception exception,
            WebRequest request) {

        log.error("An unexpected exception was thrown", exception);

        return new HttpErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                request.getDescription(false))
                .toResponseEntity();
    }
}
