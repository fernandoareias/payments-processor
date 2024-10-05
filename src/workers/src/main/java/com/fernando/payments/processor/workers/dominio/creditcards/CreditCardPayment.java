package com.fernando.payments.processor.workers.dominio.creditcards;

import com.fernando.payments.processor.core.domain.AggregateRoot;
import com.fernando.payments.processor.workers.dominio.creditcards.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "DB_CREDIT_CARD_PAYMENTS")
@Entity
@Getter
@Setter
@SuperBuilder
public class CreditCardPayment extends AggregateRoot {

    @Column
    private UUID aggregateId;

    @Column
    private PaymentStatus status;

    @Column
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payment_processor_id")
    private PaymentProcessor paymentProcessor;

    @Column
    private String cardNumber;

    @Column
    private String cardExpiry;

    @Column
    private String cardCVC;

    @Column
    private String recipientDocument;

}
