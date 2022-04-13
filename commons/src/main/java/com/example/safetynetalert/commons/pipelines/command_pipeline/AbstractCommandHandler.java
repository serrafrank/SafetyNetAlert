package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommandHandler<C extends Command>
    implements CommandHandler<C> {

    private final List<Event> events = new ArrayList<>();

    @Override
    public Void handleRequest(C request) {
        this.handler(request);
        return null;
    }

    @Override
    public void addEvent(Event event) {
        this.events.add(event);
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
