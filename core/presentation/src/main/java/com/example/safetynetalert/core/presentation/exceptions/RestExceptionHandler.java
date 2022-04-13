package com.example.safetynetalert.core.presentation.exceptions;

import com.example.safetynetalert.commons.exception.GenericBadRequestException;
import com.example.safetynetalert.commons.exception.GenericConflictException;
import com.example.safetynetalert.commons.exception.GenericInternalServerErrorException;
import com.example.safetynetalert.commons.exception.GenericNotFoundException;
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
    public ResponseEntity<HttpErrorMessage> badRequestExceptionsHandler(
        GenericBadRequestException exception,
        WebRequest request) {

        return new HttpErrorMessage(
            HttpStatus.BAD_REQUEST,
            exception,
            request)
            .toResponseEntity();
    }

    // 404
    @ExceptionHandler(GenericNotFoundException.class)
    public ResponseEntity<HttpErrorMessage> notFoundExceptionsHandler(
        GenericNotFoundException exception,
        WebRequest request) {

        return new HttpErrorMessage(
            HttpStatus.NOT_FOUND,
            exception,
            request)
            .toResponseEntity();
    }

    // 409
    @ExceptionHandler(GenericConflictException.class)
    public ResponseEntity<HttpErrorMessage> conflitExceptionsHandler(
        GenericConflictException exception,
        WebRequest request) {

        return new HttpErrorMessage(
            HttpStatus.CONFLICT,
            exception,
            request)
            .toResponseEntity();
    }

    // 409
    @ExceptionHandler({GenericInternalServerErrorException.class, Exception.class})
    public ResponseEntity<HttpErrorMessage> internalServerErrorExceptionsHandler(
        GenericConflictException exception,
        WebRequest request) {
        log.error("An unexpected exception was thrown", exception);

        return new HttpErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR,
            exception,
            request)
            .toResponseEntity();
    }
}
