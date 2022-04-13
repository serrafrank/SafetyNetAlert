package com.example.safetynetalert.commons.pipelines.event_pipeline;

import com.example.safetynetalert.commons.pipelines.Request;

public class Event extends Request {
    public Event(Request request) {
        super(request.getRequestId());
    }
}
