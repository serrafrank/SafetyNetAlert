package com.example.safetynetalert.commons.pipelines.query_pipeline;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class QueryPipelineHandlerTest {

    @Test
    void resolvesHandlersWithAGenericRequestType() {
        QueryBus queryBus = new QueryBusImpl()
                .handlers(() -> Stream.of(new QueryPipelineTypeHandler<>()));
        var request = new FooRequest<>(new BarRequest());

        String result = queryBus.dispatch(request);
        assertThat(result).isEqualTo("BarRequest");
    }

    @Test
    void handlesQueriesThatAreSubtypesOfAGenericArgument() {
        // given
        var pingHandler = new PingHandler();
        var notAPingHandler = new NotAPingHandler();

        QueryBus queryBus = new QueryBusImpl()
                .handlers(() -> Stream.of(
                        pingHandler,
                        notAPingHandler));

        // and
        var ping = new PingRequest();
        var smartPing = new SmartPingRequest();
        var notAPing = new NotAPing();

        // when
        queryBus.dispatch(ping);
        queryBus.dispatch(smartPing);
        queryBus.dispatch(notAPing);

        // then
        assertThat(pingHandler.handled).containsOnly(
                ping.getClass().getSimpleName(),
                smartPing.getClass().getSimpleName());
        assertThat(notAPingHandler.handled).containsOnly(notAPing.getClass().getSimpleName());
    }

    private static final class BarRequest
        extends Query {

        private BarRequest() {
        }
    }

    private static final class FooRequest<C>
        extends Query {

        private final C request;

        private FooRequest(C request) {
            this.request = request;
        }

        public C request() {
            return request;
        }
    }

    private static class QueryPipelineTypeHandler<C>
        implements QueryHandler<FooRequest<C>, String> {

        @Override
        public String handler(FooRequest<C> request) {
            return request.request().getClass().getSimpleName();
        }
    }

    private static class PingRequest
            extends Query {

    }

    private static class PingHandler
        implements QueryHandler<PingRequest, List<String>> {

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
            extends Query {

    }

    private static class NotAPingHandler
        implements QueryHandler<NotAPing, List<String>> {

        private final List<String> handled = new ArrayList<>();

        @Override
        public List<String> handler(NotAPing request) {
            handled.add(request.getClass().getSimpleName());
            return handled;
        }
    }
}
