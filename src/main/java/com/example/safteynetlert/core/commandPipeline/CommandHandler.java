package com.example.safteynetlert.core.commandPipeline;

import com.example.safteynetlert.core.eventPipeline.Event;
import com.example.safteynetlert.core.pipeline_builder.PipelineHandler;

import java.util.List;

public interface CommandHandler <TRequest, TReturn> extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);

    boolean addEvent(Event event);

    List<Event> events();

    boolean removeEvent(Event event);

    void clearEventsList();
}
