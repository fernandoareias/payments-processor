package com.fernando.payments.processor.webapi.application.commands;

import com.fernando.payments.processor.core.cqrs.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@SuperBuilder
public class ProcessCreditCardPaymentCommand<View> extends Command<View> {
    private final String cardNumber;
    private final String cardExpiry;   // Format: MM/YY
    private final String cardCVC;      // Card Verification Code
    private final BigDecimal amount;
    private final String recipientDocument;
}


