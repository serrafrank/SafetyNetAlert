package com.example.safetynetalert.commons.pipelines.command_pipeline;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHasMultipleHandlersException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class CommandPipelineValidatorsTest {

    @Test
    void supportsAbstractRequestHandlers() {
        // given
        HandlerThatExtendsAbstractClass handlerThatExtendsAbstractClass =
            new HandlerThatExtendsAbstractClass();

        // and
        CommandBus commandBus = new CommandBusImpl()
            .handlers(() -> Stream.of(handlerThatExtendsAbstractClass));

        // when
        commandBus.dispatch(new PingRequest("hi"));

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
        CommandBus commandBus = new CommandBusImpl()
            .handlers(() -> Stream.of(hiHandler,
                pingSaverHandler));

        // when
        commandBus.dispatch(new PingRequest("hi"));

        // and
        commandBus.dispatch(new PingRequest("bye"));

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
        CommandBus commandBus = new CommandBusImpl();

        // when
        Throwable e = assertThrows(CommandHandlerNotFoundException.class, () -> commandBus
            .dispatch(pingRequest));

        // then
        assertThat(e)
            .hasMessage("Cannot find a matching handler for PingRequest command");
    }

    @Test
    void throwsIfSentRequestHasMultipleHandlers() {
        // given
        PingRequest pingRequest = new PingRequest();
        CommandBus commandBus = new CommandBusImpl()
            .handlers(() -> Stream.of(new Pong1Handler(),
                new Pong2Handler()));

        // when
        Throwable e = assertThrows(CommandHasMultipleHandlersException.class, () -> commandBus
            .dispatch(pingRequest));

        // then
        assertThat(e)
            .hasMessage(
                "Command PingRequest must have a single matching handler, but found 2 (Pong1Handler, Pong2Handler)");
    }

    private record PingRequest(String message)
        implements Command {

        PingRequest() {
            this("Ping");
        }
    }

    private static class PingSaverHandler
        extends AbstractCommandHandler<PingRequest, String> {

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
        extends AbstractCommandHandler<PingRequest, String> {

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
        extends AbstractCommandHandler<PingRequest, String> {

        @Override
        public String handler(PingRequest request) {
            return "Pong 1";
        }
    }

    private static class Pong2Handler
        extends AbstractCommandHandler<PingRequest, String> {

        @Override
        public String handler(PingRequest request) {
            return "Pong 2";
        }
    }

    private static class HandlerThatExtendsAbstractClass
        extends AbstractCommandHandler<PingRequest, String> {

        private final Collection<PingRequest> receivedPingRequests = new ArrayList<>();

        @Override
        public String handler(PingRequest request) {
            this.receivedPingRequests.add(request);
            return receivedPingRequests.toString();
        }
    }
}
