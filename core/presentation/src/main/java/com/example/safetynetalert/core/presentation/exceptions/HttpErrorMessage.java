package com.example.safetynetalert.core.presentation.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

public class HttpErrorMessage {
    private final ZonedDateTime date;
    private final HttpStatus status;
    private final String message;
    private final String description;

    public HttpErrorMessage(HttpStatus status, String message, String description) {
        this.date = ZonedDateTime.now();
        this.status = status;
        this.message = message;
        this.description = description;
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
        return message;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }

    public ResponseEntity<HttpErrorMessage> toResponseEntity() {
        return new ResponseEntity<>(this, status);
    }
}
