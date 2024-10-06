package com.fernando.payments.processor.core.domain.interfaces;

import com.fernando.payments.processor.core.domain.comon.AggregateRoot;
import com.fernando.payments.processor.core.domain.comon.Entity;

@FunctionalInterface

public interface DomainServices<A extends AggregateRoot, E extends Entity> {
    E execute(A aggregate);
}
