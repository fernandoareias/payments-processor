package com.fernando.payments.processor.webapi.application.commands.handlers;

import com.fernando.payments.processor.core.cqrs.interfaces.CommandHandler;
import com.fernando.payments.processor.webapi.application.commands.ProcessCreditCardPaymentCommand;
import com.fernando.payments.processor.webapi.application.commands.views.ProcessCreditCardPaymentCommandView;
import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.enums.PaymentStatus;
import com.fernando.payments.processor.webapi.infrastructure.CreditCardPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ProcessCreditCardPaymentCommandHandler implements CommandHandler<ProcessCreditCardPaymentCommand<ProcessCreditCardPaymentCommandView>, ProcessCreditCardPaymentCommandView> {

    @Autowired
    private CreditCardPaymentRepository creditCardPaymentRepository;


    @Override
    public ProcessCreditCardPaymentCommandView handle(ProcessCreditCardPaymentCommand<ProcessCreditCardPaymentCommandView> command) {

        var payment = CreditCardPayment.builder()
                .createdAt(Instant.now())
                .cardNumber(command.getCardNumber())
                .amount(command.getAmount())
                .cardExpiry(command.getCardExpiry())
                .cardCVC(command.getCardCVC())
                .recipientDocument(command.getRecipientDocument())
                .status(PaymentStatus.QUEUED)
                .aggregateId(UUID.randomUUID())
                .build();

        creditCardPaymentRepository.save(payment);


        return ProcessCreditCardPaymentCommandView.builder()
                .paymentId(payment.getAggregateId())
                .status(payment.getStatus().name())
                .createdAt(Instant.now())
                .build();
    }
}
