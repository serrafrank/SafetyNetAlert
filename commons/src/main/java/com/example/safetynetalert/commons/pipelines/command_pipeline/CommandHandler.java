package com.example.safetynetalert.commons.pipelines.command_pipeline;

import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineHandler;
import java.util.List;

public interface CommandHandler<TRequest extends Command, TReturn extends Object>
    extends PipelineHandler<TRequest, TReturn> {

    TReturn handler(TRequest request);

    boolean addEvent(Event event);

    List<Event> events();

    boolean removeEvent(Event event);

    void clearEventsList();
}
