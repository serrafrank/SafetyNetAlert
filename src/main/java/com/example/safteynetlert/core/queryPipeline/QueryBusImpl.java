package com.example.safteynetlert.core.queryPipeline;

import com.example.safteynetlert.core.queryPipeline.exceptions.QueryHandlerNotFoundException;
import com.example.safteynetlert.core.queryPipeline.exceptions.QueryHasMultipleHandlersException;
import com.example.safteynetlert.core.pipeline_builder.Pipeline;
import com.example.safteynetlert.core.pipeline_builder.PipelineBuilder;
import com.example.safteynetlert.core.pipeline_builder.PipelineSupplier;
import com.example.safteynetlert.core.pipeline_builder.validators.GenericValidation;
import com.example.safteynetlert.core.pipeline_builder.validators.PipelineValidatorUtil;

public class QueryBusImpl implements QueryBus {

    private final Pipeline genericPipeline = new PipelineBuilder();

    @Override
    public QueryBusImpl handlers(PipelineSupplier.Supply<AbstractQueryHandler> QueryHandlers) {
        this.genericPipeline.handlers(QueryHandlers);
        return this;
    }

    @Override
    public QueryBusImpl middlewares(PipelineSupplier.Supply<QueryMiddleware> middlewares) {
        this.genericPipeline.middlewares(middlewares);
        return this;
    }

    @Override
    public <TQuery extends Query, TReturn> TReturn dispatch(TQuery query) {
        return this.genericPipeline.submit(query)
                .validate(handlers -> GenericValidation.from(handlers)
                        .expected(PipelineValidatorUtil.notEmpty())
                        .orThrow(() -> new QueryHandlerNotFoundException(query)))
                .validate(handlers -> GenericValidation.from(handlers)
                        .expected(PipelineValidatorUtil.onlyOne())
                        .orThrow(() -> new QueryHasMultipleHandlersException(query,
                                handlers)))
                .first();
    }
}