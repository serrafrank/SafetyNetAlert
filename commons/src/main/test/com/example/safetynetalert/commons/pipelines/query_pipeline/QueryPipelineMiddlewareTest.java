package com.example.safetynetalert.commons.pipelines.query_pipeline;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class QueryPipelineMiddlewareTest {

    @Test
    void executesRequestMiddlewaresInOrder() {
        List<String> logs = new ArrayList<>();

        var firstMiddleware = new QueryMiddleware() {
            @Override
            public <TNext> TNext invoke(Next<TNext> next) {
                logs.add("First middleware");
                var requestResponse = next.invoke();
                logs.add("First middleware");
                return requestResponse;
            }
        };

        var secondMiddleware = new QueryMiddleware() {
            @Override
            public <TNext> TNext invoke(Next<TNext> next) {
                logs.add("\tSecond middleware");
                var requestResponse = next.invoke();
                logs.add("\tSecond middleware");
                return requestResponse;
            }
        };

        record PingRequest()
            implements Query {

        }

        class ReturnTwoPipelineHandler
            extends AbstractQueryHandler<PingRequest, String> {

            @Override
            public String handler(PingRequest request) {
                return "Decorated bus execution";
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
