package com.fernando.payments.processor.webapi.application.commands.views;

import com.fernando.payments.processor.core.cqrs.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@SuperBuilder
public class ProcessCreditCardPaymentCommandView  extends View {
    private UUID paymentId;
    private Instant createdAt;
    private String status;
}
