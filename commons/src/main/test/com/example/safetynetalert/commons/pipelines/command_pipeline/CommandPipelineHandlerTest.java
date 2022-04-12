package com.example.safetynetalert.commons.pipelines.command_pipeline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CommandPipelineHandlerTest {

    @Test
    void resolvesHandlersWithAGenericRequestType() {
        CommandBus commandBus = new CommandBusImpl()
                .handlers(() -> Stream.of(new CommandPipelineTypeHandler<>()));
        var request = new FooRequest<>(new BarRequest());

        String result = commandBus.dispatch(request);
        assertThat(result).isEqualTo("BarRequest");
    }

    @Test
    void handlesQueriesThatAreSubtypesOfAGenericArgument() {
        // given
        var pingHandler = new PingHandler();
        var notAPingHandler = new NotAPingHandler();

        CommandBus commandBus = new CommandBusImpl()
                .handlers(() -> Stream.of(
                        pingHandler,
                        notAPingHandler));

        // and
        var ping = new PingRequest();
        var smartPing = new SmartPingRequest();
        var notAPing = new NotAPing();

        // when
        commandBus.dispatch(ping);
        commandBus.dispatch(smartPing);
        commandBus.dispatch(notAPing);

        // then
        assertThat(pingHandler.handled).containsOnly(
                ping.getClass().getSimpleName(),
                smartPing.getClass().getSimpleName());
        assertThat(notAPingHandler.handled).containsOnly(notAPing.getClass().getSimpleName());
    }

    private record BarRequest()
            implements Command {

    }

    private record FooRequest<C>(C request)
            implements Command {

    }

    private static class CommandPipelineTypeHandler<C>
            extends AbstractCommandHandler<FooRequest<C>, String> {

        @Override
        public String handler(FooRequest<C> request) {
            return request.request().getClass().getSimpleName();
        }
    }

    private static class PingRequest
            implements Command {

    }

    private static class PingHandler
            extends AbstractCommandHandler<PingRequest, List<String>> {

        private final List<String> handled = new ArrayList<>();

        @Override
        public List<String> handler(PingRequest request) {
            handled.add(request.getClass().getSimpleName());
            return handled;
        }
    }

    private static class SmartPingRequest
            extends PingRequest {

    }

    private static class NotAPing
            implements Command {

    }

    private static class NotAPingHandler
            extends AbstractCommandHandler<NotAPing, List<String>> {

        private final List<String> handled = new ArrayList<>();

        @Override
        public List<String> handler(NotAPing request) {
            handled.add(request.getClass().getSimpleName());
            return handled;
        }
    }
}
