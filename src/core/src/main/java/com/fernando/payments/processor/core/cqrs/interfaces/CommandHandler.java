package com.fernando.payments.processor.core.cqrs.interfaces;

import com.fernando.payments.processor.core.cqrs.Command;
import com.fernando.payments.processor.core.cqrs.View;

@FunctionalInterface
public interface CommandHandler<T extends Command<V>, V> {
    V handle(T command);
}
