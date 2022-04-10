package com.example.safetynetalert.commons.pipeline_builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class PipelineMiddlewareTest {

    @Test
    void executesRequestMiddlewaresInOrder() {
        List<String> logs = new ArrayList<>();

        var firstMiddleware = new PipelineMiddleware() {
            @Override
            public <TNext> TNext invoke(Next<TNext> next) {
                logs.add("First middleware");
                var requestResponse = next.invoke();
                logs.add("First middleware");
                return requestResponse;
            }
        };

        var secondMiddleware = new PipelineMiddleware() {
            @Override
            public <TNext> TNext invoke(Next<TNext> next) {
                logs.add("\tSecond middleware");
                var requestResponse = next.invoke();
                logs.add("\tSecond middleware");
                return requestResponse;
            }
        };

        record PingRequest()
            implements Serializable {

        }

        class ReturnTwoPipelineHandler
            implements PipelineHandler<PingRequest, String> {

            @Override
            public String handleRequest(PingRequest request) {
                return "Decorated bus execution";
            }
        }

        Pipeline pipeline =
            new PipelineBuilder()
                .handlers(() -> Stream.of(new ReturnTwoPipelineHandler()))
                .middlewares(() -> Stream.of(firstMiddleware, secondMiddleware));

        // when
        List<String> responseList = pipeline.submit(new PingRequest()).dispatch();
        assertThat(responseList).hasSize(1);

        // then
        List<String> expectedLogs = List.of("First middleware",
            "\tSecond middleware",
            "\tSecond middleware",
            "First middleware"
        );

        assertEquals(expectedLogs, logs);
        assertEquals("Decorated bus execution", responseList.get(0));

    }

}
