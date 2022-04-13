package com.example.safetynetalert.commons.pipelines.command_pipeline;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class CommandPipelineHandlerTest {

    @Test
    void resolvesHandlersWithAGenericRequestType() {
        var handler = new CommandPipelineTypeHandler<>();
        CommandBus commandBus = new CommandBusImpl()
            .handlers(() -> Stream.of(handler));
        var request = new FooRequest<>(new BarRequest());

        commandBus.dispatch(request);

        String result = handler.requestName;
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

    private static final class BarRequest extends Command {

    }

    private static final class FooRequest<C>
        extends Command {

        private final C request;

        private FooRequest(C request) {
            this.request = request;
        }

        public C request() {
            return request;
        }
    }

    private static class CommandPipelineTypeHandler<C>
        extends AbstractCommandHandler<FooRequest<C>> {

        String requestName;

        @Override
        public void handler(FooRequest<C> request) {
            requestName = request.request().getClass().getSimpleName();
        }
    }

    private static class PingRequest
        extends Command {

    }

    private static class PingHandler
        extends AbstractCommandHandler<PingRequest> {

        final List<String> handled = new ArrayList<>();

        @Override
        public void handler(PingRequest request) {
            handled.add(request.getClass().getSimpleName());
        }
    }

    private static class SmartPingRequest
        extends PingRequest {

    }

    private static class NotAPing
        extends Command {

    }

    private static class NotAPingHandler
        extends AbstractCommandHandler<NotAPing> {

        private final List<String> handled = new ArrayList<>();

        @Override
        public void handler(NotAPing request) {
            handled.add(request.getClass().getSimpleName());
        }
    }
}
