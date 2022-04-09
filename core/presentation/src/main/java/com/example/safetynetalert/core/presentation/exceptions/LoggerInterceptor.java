package com.example.safetynetalert.core.presentation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@ControllerAdvice
public class LoggerInterceptor
    implements HandlerInterceptor {

    @ExceptionHandler(AbstractResponseStatusException.class)
    public void handleBaseException(AbstractResponseStatusException e) {
        log.info("A response exception "
                 + e.getClass().getSimpleName()
                 + " was thrown with message '"
                 + e.getMessage() + "'");
        throw e;
    }

    @ExceptionHandler(Exception.class)
    public void handleGeneralException(Exception e) throws Exception {
        log.error("An unexpected exception was thrown", e);
        throw e;
    }

}
