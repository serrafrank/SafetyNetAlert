package com.example.safetynetalert.commons.pipelines.event_pipeline.exceptions;

import com.example.safetynetalert.commons.exception.GenericInternalServerErrorException;
import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;

public class EventHandlerNotFoundException
        extends GenericInternalServerErrorException {

    private final String eventClass;

    public <E extends Event> EventHandlerNotFoundException(
            E event) {
        this.eventClass = event.getClass().getSimpleName();
    }

    @Override
    public String getMessage() {
        return "Cannot find a matching handler for " + eventClass + " event";
    }
}
