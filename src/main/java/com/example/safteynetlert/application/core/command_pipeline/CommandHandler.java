package com.example.safteynetlert.application.core.command_pipeline;

import com.example.safteynetlert.application.core.event_pipeline.Event;
import com.example.safteynetlert.domaine.pipeline_builder.PipelineHandler;

import java.util.List;

public interface CommandHandler<TRequest, TReturn> extends PipelineHandler<TRequest, TReturn> {
    TReturn handler(TRequest request);

    boolean addEvent(Event event);

    List<Event> events();

    boolean removeEvent(Event event);

    void clearEventsList();
}
