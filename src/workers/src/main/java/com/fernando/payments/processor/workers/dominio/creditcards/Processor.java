package com.fernando.payments.processor.workers.dominio.creditcards;

import com.fernando.payments.processor.core.domain.Entity;
import com.fernando.payments.processor.workers.dominio.creditcards.enums.ProcessorStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table(name = "DB_PROCESSOR")
@jakarta.persistence.Entity
@AllArgsConstructor
@Getter
@Setter
public class Processor extends Entity {
    private String externalId;
    private ProcessorStatus status;
    private String response;

    @ManyToOne
    @JoinColumn(name = "processors")
    private PaymentProcessor paymentProcessor;
}
