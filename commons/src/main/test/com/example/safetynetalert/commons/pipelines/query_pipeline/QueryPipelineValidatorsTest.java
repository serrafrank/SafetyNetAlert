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
                .hasMessage(
                        "Query PingRequest must have a single matching handler, but found 2 (Pong1Handler, Pong2Handler)");
    }

    private static final class PingRequest
        extends Query {

        private final String message;

        private PingRequest(String message) {
            this.message = message;
        }

        PingRequest() {
            this("Ping");
        }

        @Override
        public String toString() {
            return "PingRequest[" +
                   "message=" + message + ']';
        }
    }

    private static class PingSaverHandler
        implements QueryHandler<PingRequest, String> {

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
        implements QueryHandler<PingRequest, String> {

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
        implements QueryHandler<PingRequest, String> {

        @Override
        public String handler(PingRequest request) {
            return "Pong 1";
        }
    }

    private static class Pong2Handler
        implements QueryHandler<PingRequest, String> {

        @Override
        public String handler(PingRequest request) {
            return "Pong 2";
        }
    }

    static class HandlerThatExtendsAbstractClass
        implements QueryHandler<PingRequest, String> {

        private final Collection<PingRequest> receivedPingRequests = new ArrayList<>();

        @Override
        public String handler(PingRequest request) {
            this.receivedPingRequests.add(request);
            return receivedPingRequests.toString();
        }

        public Collection<PingRequest> getReceivedPingRequests() {
            return receivedPingRequests;
        }
    }
}
