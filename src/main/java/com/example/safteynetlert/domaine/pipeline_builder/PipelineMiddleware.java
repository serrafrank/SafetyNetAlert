package com.example.safteynetlert.domaine.pipeline_builder;

@FunctionalInterface
public interface PipelineMiddleware {

    <TNext> TNext invoke(Next<TNext> next);

    interface Next<TReturn> {

        TReturn invoke();
    }
}
