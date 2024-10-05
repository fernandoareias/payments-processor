package com.fernando.payments.processor.core.cqrs.interfaces;

import com.fernando.payments.processor.core.cqrs.Command;
import com.fernando.payments.processor.core.cqrs.Query;

public interface Mediator {
    <TResponse> TResponse send(Command<TResponse> command);
    <TResponse> TResponse send(Query<TResponse> query);
}
