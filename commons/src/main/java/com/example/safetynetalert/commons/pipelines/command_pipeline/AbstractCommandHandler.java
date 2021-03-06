package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommandHandler<TRequest extends Command, TReturn extends Object>
    implements CommandHandler<TRequest, TReturn> {

    private final List<Event> events = new ArrayList<>();

    @Override
    public TReturn handleRequest(TRequest request) {
        return this.handler(request);
    }

    @Override
    public boolean addEvent(Event event) {
        return this.events.add(event);
    }

    @Override
    public List<Event> events() {
        return this.events;
    }

    @Override
    public boolean removeEvent(Event event) {
        return this.events.remove(event);
    }

    @Override
    public void clearEventsList() {
        this.events.clear();
    }

}
