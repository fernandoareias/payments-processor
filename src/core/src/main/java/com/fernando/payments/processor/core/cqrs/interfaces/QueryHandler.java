package com.fernando.payments.processor.core.cqrs.interfaces;

import com.fernando.payments.processor.core.cqrs.Query;

import java.util.List;

@FunctionalInterface
public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}