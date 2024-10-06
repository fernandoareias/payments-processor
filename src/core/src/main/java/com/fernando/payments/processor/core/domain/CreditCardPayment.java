package com.fernando.payments.processor.core.domain;

import com.fernando.payments.processor.core.domain.comon.AggregateRoot;
import com.fernando.payments.processor.core.domain.enums.PaymentStatus;
import com.fernando.payments.processor.core.domain.interfaces.DomainServices;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "creditCardPayment")
    private Set<Processor> processors;



    public void ProcessPayment(DomainServices<CreditCardPayment, Processor> domainService)
    {
        var processor = domainService.execute(this);

        if(processor == null)
            throw new IllegalStateException("Processor is null");

        if (processors == null) {
            processors = new HashSet<>();
        }

        processors.add(processor);

        UpdateStatus(processor);
    }

    public void UpdateStatus(Processor processor)
    {
        switch (processor.getStatus())  {
            case PROCESSED -> status = PaymentStatus.COMPLETED;
            case ERROR -> status = PaymentStatus.FAILED;
            default -> throw new IllegalStateException("Invalid processor status");
        }
    }

}
