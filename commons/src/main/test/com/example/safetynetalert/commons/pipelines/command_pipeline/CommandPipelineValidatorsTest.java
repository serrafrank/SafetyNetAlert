package com.example.safetynetalert.commons.pipelines.command_pipeline;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.command_pipeline.exceptions.CommandHasMultipleHandlersException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class CommandPipelineValidatorsTest {

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

    private static final class PingRequest
            extends Command {
    }

    private static class Pong1Handler
        extends AbstractCommandHandler<PingRequest> {

        @Override
        public void handler(PingRequest request) {
        }
    }

    private static class Pong2Handler
        extends AbstractCommandHandler<PingRequest> {

        @Override
        public void handler(PingRequest request) {
        }
    }
}
