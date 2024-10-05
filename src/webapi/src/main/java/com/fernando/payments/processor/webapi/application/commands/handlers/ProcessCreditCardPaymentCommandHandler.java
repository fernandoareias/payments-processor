package com.fernando.payments.processor.webapi.application.commands.handlers;

import com.fernando.payments.processor.core.cqrs.View;
import com.fernando.payments.processor.core.cqrs.interfaces.CommandHandler;
import com.fernando.payments.processor.webapi.application.commands.ProcessCreditCardPaymentCommand;
import com.fernando.payments.processor.webapi.application.commands.views.ProcessCreditCardPaymentCommandView;
import com.fernando.payments.processor.webapi.domain.creditcards.CreditCardPayment;
import com.fernando.payments.processor.webapi.domain.creditcards.interfaces.CreditCardPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProcessCreditCardPaymentCommandHandler implements CommandHandler<ProcessCreditCardPaymentCommand<ProcessCreditCardPaymentCommandView>, ProcessCreditCardPaymentCommandView> {

    @Autowired
    private CreditCardPaymentRepository creditCardPaymentRepository;


    @Override
    public ProcessCreditCardPaymentCommandView handle(ProcessCreditCardPaymentCommand<ProcessCreditCardPaymentCommandView> command) {

        var payment = new CreditCardPayment(
                command.getAmount(),
                command.getCardNumber(),
                command.getCardExpiry(),
                command.getCardCVC(),
                command.getRecipientDocument());

        creditCardPaymentRepository.save(payment);


        return ProcessCreditCardPaymentCommandView.builder()
                .paymentId(payment.getAggregateId())
                .status(payment.getStatus().name())
                .createdAt(Instant.now())
                .build();
    }
}
