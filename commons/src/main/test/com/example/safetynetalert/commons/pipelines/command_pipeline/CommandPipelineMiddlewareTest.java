package com.example.safetynetalert.commons.pipelines.command_pipeline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class CommandPipelineMiddlewareTest {

    @Test
    void executesRequestMiddlewaresInOrder() {
        List<String> logs = new ArrayList<>();

        var firstMiddleware = new CommandMiddleware() {
            @Override
            public <TNext> TNext invoke(Next<TNext> next) {
                logs.add("First middleware");
                var requestResponse = next.invoke();
                logs.add("First middleware");
                return requestResponse;
            }
        };

        var secondMiddleware = new CommandMiddleware() {
            @Override
            public <TNext> TNext invoke(Next<TNext> next) {
                logs.add("\tSecond middleware");
                var requestResponse = next.invoke();
                logs.add("\tSecond middleware");
                return requestResponse;
            }
        };

        CommandBus commandBus = new CommandBusImpl()
                .handlers(() -> Stream.of(new ReturnTwoPipelineHandler()))
                .middlewares(() -> Stream.of(firstMiddleware, secondMiddleware));

        // when
       commandBus.dispatch(new PingRequest());

        // then
        List<String> expectedLogs = List.of("First middleware",
                "\tSecond middleware",
                "\tSecond middleware",
                "First middleware"
        );

        assertEquals(expectedLogs, logs);

    }

    static final class PingRequest
            extends Command {

    }

    static class ReturnTwoPipelineHandler
        extends AbstractCommandHandler<PingRequest> {

        @Override
        public void handler(PingRequest request) {
        }
    }

}
