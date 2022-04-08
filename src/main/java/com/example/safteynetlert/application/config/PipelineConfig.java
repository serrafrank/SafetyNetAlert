package com.example.safteynetlert.application.config;

import com.example.safteynetlert.application.core.commandPipeline.CommandBus;
import com.example.safteynetlert.application.core.commandPipeline.CommandBusImpl;
import com.example.safteynetlert.application.core.commandPipeline.CommandHandler;
import com.example.safteynetlert.application.core.commandPipeline.CommandMiddleware;
import com.example.safteynetlert.application.core.eventPipeline.EventBusImpl;
import com.example.safteynetlert.application.core.eventPipeline.EventHandler;
import com.example.safteynetlert.application.core.eventPipeline.EventMiddleware;
import com.example.safteynetlert.application.core.queryPipeline.AbstractQueryHandler;
import com.example.safteynetlert.application.core.queryPipeline.QueryBus;
import com.example.safteynetlert.application.core.queryPipeline.QueryBusImpl;
import com.example.safteynetlert.application.core.queryPipeline.QueryMiddleware;
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
