package com.example.safetynetalert.core.application.config;

import com.example.safetynetalert.commons.pipelines.command_pipeline.Command;
import com.example.safetynetalert.commons.pipelines.command_pipeline.CommandBus;
import com.example.safetynetalert.commons.pipelines.command_pipeline.CommandBusImpl;
import com.example.safetynetalert.commons.pipelines.command_pipeline.CommandHandler;
import com.example.safetynetalert.commons.pipelines.command_pipeline.CommandMiddleware;
import com.example.safetynetalert.commons.pipelines.event_pipeline.Event;
import com.example.safetynetalert.commons.pipelines.event_pipeline.EventBusImpl;
import com.example.safetynetalert.commons.pipelines.event_pipeline.EventHandler;
import com.example.safetynetalert.commons.pipelines.event_pipeline.EventMiddleware;
import com.example.safetynetalert.commons.pipelines.query_pipeline.Query;
import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryBus;
import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryBusImpl;
import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryHandler;
import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryMiddleware;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfig {

    @Bean
    CommandBus commandBus(
        ObjectProvider<CommandHandler<? extends Command, ?>> commandHandlers,
        ObjectProvider<CommandMiddleware> middlewares,
        ObjectProvider<EventHandler<? extends Event, ?>> eventHandlers,
        ObjectProvider<EventMiddleware> eventMiddlewares
    ) {

        var eventBus = new EventBusImpl()
            .handlers(eventHandlers::stream)
            .middlewares(eventMiddlewares::orderedStream);

        return new CommandBusImpl()
            .handlers(commandHandlers::stream)
            .middlewares(middlewares::orderedStream)
            .eventBus(eventBus);
    }

    @Bean
    QueryBus queryBus(ObjectProvider<QueryHandler<? extends Query, ?>> queryHandlers,
                      ObjectProvider<QueryMiddleware> middlewares) {
        return new QueryBusImpl()
            .handlers(queryHandlers::stream)
            .middlewares(middlewares::orderedStream);
    }
}
