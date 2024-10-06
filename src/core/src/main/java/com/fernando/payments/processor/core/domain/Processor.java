package com.fernando.payments.processor.core.domain;

import com.fernando.payments.processor.core.domain.comon.Entity;
import com.fernando.payments.processor.core.domain.enums.ProcessorStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "DB_PROCESSOR")
@jakarta.persistence.Entity
@Getter
@Setter
@SuperBuilder
public class Processor extends Entity {
    private String externalId;
    private ProcessorStatus status;
    private String response;

    @ManyToOne
    @JoinColumn(name = "processors")
    private CreditCardPayment creditCardPayment;
}
