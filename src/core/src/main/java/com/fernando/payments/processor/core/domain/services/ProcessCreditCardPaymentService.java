package com.fernando.payments.processor.core.domain.services;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.PaymentProcessor;
import com.fernando.payments.processor.core.domain.Processor;
import com.fernando.payments.processor.core.domain.enums.ProcessorStatus;
import com.fernando.payments.processor.core.domain.interfaces.DomainServices;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class ProcessCreditCardPaymentService implements DomainServices<CreditCardPayment, Processor> {
    @Override
    public Processor execute(CreditCardPayment aggregate) {

        var randomNumber =  Math.ceil(Math.random() * 10);

        var status = randomNumber % 2 == 0 ? ProcessorStatus.PROCESSED : ProcessorStatus.ERROR;

        return Processor.builder()
                .creditCardPayment(aggregate)
                .createdAt(Instant.now())
                .status(status)
                .externalId(status == ProcessorStatus.PROCESSED ? UUID.randomUUID().toString() : null)
                .response(status == ProcessorStatus.PROCESSED ? "{ \"transactionId\": \"1234567890\", \"processedAt\": \"2024-10-06T14:30:00Z\", \"status\": \"SUCCEEDED\" }" : "{ \"transactionId\": \"1234567890\", \"processedAt\": \"2024-10-06T14:30:00Z\", \"status\": \"FAILED\" }")
                .build();

    }
}
