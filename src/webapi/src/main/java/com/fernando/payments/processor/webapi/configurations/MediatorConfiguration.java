package com.fernando.payments.processor.webapi.configurations;

import com.fernando.payments.processor.core.cqrs.interfaces.CommandHandler;
import com.fernando.payments.processor.core.cqrs.interfaces.Mediator;
import com.fernando.payments.processor.core.cqrs.interfaces.QueryHandler;
import com.fernando.payments.processor.core.infrastructure.SimpleMediator;
import com.fernando.payments.processor.webapi.application.commands.ProcessCreditCardPaymentCommand;
import com.fernando.payments.processor.webapi.application.commands.handlers.ProcessCreditCardPaymentCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MediatorConfiguration {

    @Bean
    public Mediator mediator(ProcessCreditCardPaymentCommandHandler processCreditCardPaymentCommandHandler) {

        Map<Class<?>, CommandHandler<?, ?>> commandHandlers = new HashMap<>();
        Map<Class<?>, QueryHandler<?, ?>> queryHandlers = new HashMap<>();

        commandHandlers.put(ProcessCreditCardPaymentCommand.class, processCreditCardPaymentCommandHandler);


        return new SimpleMediator(commandHandlers, queryHandlers);
    }
}