package com.fernando.payments.processor.webapi.domain.creditcards;

import com.fernando.payments.processor.core.domain.AggregateRoot;
import com.fernando.payments.processor.webapi.domain.creditcards.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "DB_CREDIT_CARD_PAYMENTS")
@Entity
@AllArgsConstructor
@Getter
@Setter
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


    public CreditCardPayment(BigDecimal amount, String cardNumber, String cardExpiry, String cardCVC, String recipientDocument) {
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cardCVC = cardCVC;
        this.recipientDocument = recipientDocument;
        this.status = PaymentStatus.QUEUED;
        aggregateId = UUID.randomUUID();
    }
}
