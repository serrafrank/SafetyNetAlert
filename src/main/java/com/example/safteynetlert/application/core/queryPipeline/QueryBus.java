package com.example.safteynetlert.application.core.queryPipeline;

import com.example.safteynetlert.domaine.pipeline_builder.PipelineSupplier;

public interface QueryBus {

    QueryBusImpl handlers(PipelineSupplier.Supply<AbstractQueryHandler> requestHandlers);

    QueryBusImpl middlewares(PipelineSupplier.Supply<QueryMiddleware> middlewares);

    <TRequest extends Query, TReturn> TReturn dispatch(TRequest request);
}
