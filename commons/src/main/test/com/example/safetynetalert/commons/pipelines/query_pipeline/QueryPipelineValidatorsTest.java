package com.example.safetynetalert.commons.pipelines.query_pipeline;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.safetynetalert.commons.pipelines.query_pipeline.exceptions.QueryHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.query_pipeline.exceptions.QueryHasMultipleHandlersException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class QueryPipelineValidatorsTest {

    @Test
    void supportsAbstractRequestHandlers() {
        // given
        HandlerThatExtendsAbstractClass handlerThatExtendsAbstractClass =
            new HandlerThatExtendsAbstractClass();

        // and
        QueryBus queryBus = new QueryBusImpl()
            .handlers(() -> Stream.of(handlerThatExtendsAbstractClass));

        // when
        queryBus.dispatch(new PingRequest("hi"));

        // then:
        assertThat(handlerThatExtendsAbstractClass.receivedPingRequests)
            .containsOnly(new PingRequest("hi"));
    }

    @Test
    void supportsCustomHandlerMatching() {
        // given
        HiHandler hiHandler = new HiHandler();
        PingSaverHandler pingSaverHandler = new PingSaverHandler();

        // and
        QueryBus queryBus = new QueryBusImpl()
            .handlers(() -> Stream.of(hiHandler,
                pingSaverHandler));


        // when
        queryBus.dispatch(new PingRequest("hi"));

        // and
        queryBus.dispatch(new PingRequest("bye"));

        // then
        assertThat(hiHandler.receivedPingRequests)
            .containsOnly(new PingRequest("hi"));

        // and
        assertThat(pingSaverHandler.receivedPingRequests)
            .containsOnly(new PingRequest("bye"));
    }

    @Test
    void throwsIfSentRequestHasNoMatchingHandler() {
        // given
        PingRequest pingRequest = new PingRequest();
        QueryBus queryBus = new QueryBusImpl();

        // when
        Throwable e = assertThrows(QueryHandlerNotFoundException.class, () -> queryBus
            .dispatch(pingRequest));

        // then
        assertThat(e)
            .hasMessage("Cannot find a matching handler for PingRequest query");
    }

    @Test
    void throwsIfSentRequestHasMultipleHandlers() {
        // given
        PingRequest pingRequest = new PingRequest();
        QueryBus queryBus = new QueryBusImpl()
            .handlers(() -> Stream.of(new Pong1Handler(),
                new Pong2Handler()));

        // when
        Throwable e = assertThrows(QueryHasMultipleHandlersException.class, () -> queryBus
            .dispatch(pingRequest));

        // then
        assertThat(e)
            .hasMessage("Query PingRequest must have a single matching handler, but found 2 (Pong1Handler, Pong2Handler)");
    }

    private record PingRequest(String message)
        implements Query {

        PingRequest() {
            this("Ping");
        }
    }

    private static class PingSaverHandler
        extends AbstractQueryHandler<PingRequest, String> {

        private final Collection<PingRequest> receivedPingRequests = new ArrayList<>();


        @Override
        public String handler(PingRequest request) {
            this.receivedPingRequests.add(request);
            return receivedPingRequests.toString();
        }

        @Override
        public boolean matches(PingRequest request) {
            return request.message.equals("bye");
        }
    }

    private static class HiHandler
        extends AbstractQueryHandler<PingRequest, String> {

        private final Collection<PingRequest> receivedPingRequests = new ArrayList<>();

        @Override
        public String handler(PingRequest request) {
            this.receivedPingRequests.add(request);
            return receivedPingRequests.toString();
        }

        @Override
        public boolean matches(PingRequest request) {
            return request.message.equals("hi");
        }

    }

    private static class Pong1Handler
        extends AbstractQueryHandler<PingRequest, String> {

        @Override
        public String handler(PingRequest request) {
            return "Pong 1";
        }
    }

    private static class Pong2Handler
        extends AbstractQueryHandler<PingRequest, String> {

        @Override
        public String handler(PingRequest request) {
            return "Pong 2";
        }
    }

    private static class HandlerThatExtendsAbstractClass
        extends AbstractQueryHandler<PingRequest, String> {

        private final Collection<PingRequest> receivedPingRequests = new ArrayList<>();

        @Override
        public String handler(PingRequest request) {
            this.receivedPingRequests.add(request);
            return receivedPingRequests.toString();
        }
    }
}
