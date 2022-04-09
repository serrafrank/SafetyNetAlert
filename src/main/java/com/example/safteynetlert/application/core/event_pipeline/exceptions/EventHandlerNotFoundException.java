package com.example.safteynetlert.application.core.event_pipeline.exceptions;

import com.example.safteynetlert.application.core.event_pipeline.Event;

public class EventHandlerNotFoundException
        extends RuntimeException {

    private final String eventClass;

    public <TEvent extends Event> EventHandlerNotFoundException(
            TEvent event) {
        this.eventClass = event.getClass().getSimpleName();
    }

    @Override
    public String getMessage() {
        return "Cannot find a matching handler for " + eventClass + " event";
    }
}
