package com.example.safetynetalert.commons.pipelines.query_pipeline;

import com.example.safetynetalert.commons.pipeline_builder.Pipeline;
import com.example.safetynetalert.commons.pipeline_builder.PipelineBuilder;
import com.example.safetynetalert.commons.pipeline_builder.PipelineSupplier;
import com.example.safetynetalert.commons.pretty_validator.PrettyValidation;
import com.example.safetynetalert.commons.pipeline_builder.validators.PipelineValidatorUtil;
import com.example.safetynetalert.commons.pipelines.query_pipeline.exceptions.QueryHandlerNotFoundException;
import com.example.safetynetalert.commons.pipelines.query_pipeline.exceptions.QueryHasMultipleHandlersException;

public class QueryBusImpl
    implements QueryBus {

    private final Pipeline genericPipeline = new PipelineBuilder();

    @Override
    public QueryBusImpl handlers(PipelineSupplier.Supply<QueryHandler<? extends Query, ?>> QueryHandlers) {
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
            .validate(handlers -> PrettyValidation.test(
                handlers)
                .is(PipelineValidatorUtil.notEmpty())
                .orThrow(() -> new QueryHandlerNotFoundException(
                    query)))
            .validate(handlers -> PrettyValidation.test(
                handlers)
                .is(PipelineValidatorUtil.onlyOne())
                .orThrow(() -> new QueryHasMultipleHandlersException(
                    query,
                    handlers)))
            .first();
    }
}
