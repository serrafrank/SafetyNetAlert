package com.example.safetynetalert.commons.query_pipeline;

import com.example.safetynetalert.commons.pipelines.pipeline_builder.PipelineSupplier;

public interface QueryBus {

    QueryBusImpl handlers(PipelineSupplier.Supply<QueryHandler<? extends Query, ?>> requestHandlers);

    QueryBusImpl middlewares(PipelineSupplier.Supply<QueryMiddleware> middlewares);

    <TRequest extends Query, TReturn> TReturn dispatch(TRequest request);
}
