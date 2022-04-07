package com.example.safteynetlert.core.queryPipeline;

import com.example.safteynetlert.core.pipeline_builder.PipelineSupplier;

public interface QueryBus {

    QueryBusImpl handlers(PipelineSupplier.Supply<AbstractQueryHandler> requestHandlers);

    QueryBusImpl middlewares(PipelineSupplier.Supply<QueryMiddleware> middlewares);

    <TRequest extends Query, TReturn> TReturn dispatch(TRequest request);
}
