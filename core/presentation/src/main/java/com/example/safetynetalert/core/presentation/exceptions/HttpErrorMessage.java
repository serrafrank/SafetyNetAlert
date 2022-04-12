package com.example.safetynetalert.core.presentation.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

public class HttpErrorMessage {
    private final ZonedDateTime date;
    private final HttpStatus status;
    private final Exception exception;
    private final WebRequest request;

    public HttpErrorMessage(
            HttpStatus status,
            Exception exception,
            WebRequest request) {
        this.date = ZonedDateTime.now();
        this.status = status;
        this.exception = exception;
        this.request = request;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "MM/dd/yyyy - HH:mm:ss Z")
    public ZonedDateTime getDate() {
        return date;
    }

    @JsonGetter
    public int getStatus() {
        return status.value();
    }

    @JsonGetter
    public String getError() {
        return status.getReasonPhrase();
    }

    @JsonGetter
    public String getMessage() {
        return exception.getMessage();
    }

    @JsonGetter
    public String getDescription() {
        return request.getDescription(false);
    }

    public ResponseEntity<HttpErrorMessage> toResponseEntity() {
        return new ResponseEntity<>(this, status);
    }
}
