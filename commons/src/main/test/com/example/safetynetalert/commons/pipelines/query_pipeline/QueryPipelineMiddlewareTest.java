package com.example.safetynetalert.commons.pipelines.query_pipeline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryPipelineMiddlewareTest {

    @Test
    void executesRequestMiddlewaresInOrder() {
        List<String> logs = new ArrayList<>();

        var firstMiddleware = new record PingRequest()
                implements Query {

        }

        var secondMiddleware = new
        class ReturnTwoPipelineHandler
                extends AbstractQueryHandler<PingRequest, String> {

            @Override
            public String handler(PingRequest request) {
                return "Decorated bus execution";
            }
        }

        QueryMiddleware() {
            @Override
            public <TNext > TNext invoke(Next < TNext > next) {
                logs.add("First middleware");
                var requestResponse = next.invoke();
                logs.add("First middleware");
                return requestResponse;
            }
        }

        QueryMiddleware() {
            @Override
            public <TNext > TNext invoke(Next < TNext > next) {
                logs.add("\tSecond middleware");
                var requestResponse = next.invoke();
                logs.add("\tSecond middleware");
                return requestResponse;
            }
        }

        QueryBus queryBus = new QueryBusImpl()
                .handlers(() -> Stream.of(new ReturnTwoPipelineHandler()))
                .middlewares(() -> Stream.of(firstMiddleware, secondMiddleware));

        // when
        List<String> responseList = queryBus.dispatch(new PingRequest());
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
