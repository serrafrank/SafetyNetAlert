package com.example.safteynetlert.application.config;

import com.example.safteynetlert.application.core.command_pipeline.CommandBus;
import com.example.safteynetlert.application.core.command_pipeline.CommandBusImpl;
import com.example.safteynetlert.application.core.command_pipeline.CommandHandler;
import com.example.safteynetlert.application.core.command_pipeline.CommandMiddleware;
import com.example.safteynetlert.application.core.event_pipeline.EventBusImpl;
import com.example.safteynetlert.application.core.event_pipeline.EventHandler;
import com.example.safteynetlert.application.core.event_pipeline.EventMiddleware;
import com.example.safteynetlert.application.core.query_pipeline.AbstractQueryHandler;
import com.example.safteynetlert.application.core.query_pipeline.QueryBus;
import com.example.safteynetlert.application.core.query_pipeline.QueryBusImpl;
import com.example.safteynetlert.application.core.query_pipeline.QueryMiddleware;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineConfig {

    @Bean
    CommandBus commandBus(
        ObjectProvider<CommandHandler> commandHandlers,
        ObjectProvider<CommandMiddleware> middlewares,
        ObjectProvider<EventHandler> eventHandlers,
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
    QueryBus queryBus(ObjectProvider<AbstractQueryHandler> queryHandlers,
                      ObjectProvider<QueryMiddleware> middlewares) {
        return new QueryBusImpl()
            .handlers(queryHandlers::stream)
            .middlewares(middlewares::orderedStream);
    }
}
