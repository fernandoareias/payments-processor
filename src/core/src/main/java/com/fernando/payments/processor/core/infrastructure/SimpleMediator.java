package com.fernando.payments.processor.core.infrastructure;

import com.fernando.payments.processor.core.cqrs.Command;
import com.fernando.payments.processor.core.cqrs.Query;
import com.fernando.payments.processor.core.cqrs.interfaces.CommandHandler;
import com.fernando.payments.processor.core.cqrs.interfaces.Mediator;
import com.fernando.payments.processor.core.cqrs.interfaces.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleMediator implements Mediator {

    private final Map<Class<?>, CommandHandler<?, ?>> commandHandlers;
    private final Map<Class<?>, QueryHandler<?, ?>> queryHandlers;

    public SimpleMediator(Map<Class<?>, CommandHandler<?, ?>> commandHandlers,
                          Map<Class<?>, QueryHandler<?, ?>> queryHandlers) {
        this.commandHandlers = commandHandlers;
        this.queryHandlers = queryHandlers;
    }

    @Override
    public <TResponse> TResponse send(Command<TResponse> command) {
        CommandHandler<Command<TResponse>, TResponse> handler =
                (CommandHandler<Command<TResponse>, TResponse>) commandHandlers.get(command.getClass());

        if (handler == null) {
            throw new IllegalArgumentException("No command handler found for command type: " + command.getClass().getName());
        }

        return handler.handle(command);
    }

    @Override
    public <TResponse> TResponse send(Query<TResponse> query) {
        QueryHandler<Query<TResponse>, TResponse> handler =
                (QueryHandler<Query<TResponse>, TResponse>) queryHandlers.get(query.getClass());

        if (handler == null) {
            throw new IllegalArgumentException("No query handler found for query type: " + query.getClass().getName());
        }

        return handler.handle(query);
    }
}
