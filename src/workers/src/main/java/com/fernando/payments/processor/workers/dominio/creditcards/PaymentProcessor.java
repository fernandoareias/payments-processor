package com.fernando.payments.processor.workers.dominio.creditcards;

import com.fernando.payments.processor.core.domain.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table(name = "DB_PAYMENT_PROCESSOR")
@jakarta.persistence.Entity
@AllArgsConstructor
@Getter
@Setter
public class PaymentProcessor extends Entity {



    private String name;

    @OneToMany(mappedBy = "paymentProcessor")
    private Set<CreditCardPayment> payments;

    @OneToMany(mappedBy = "paymentProcessor")
    private Set<Processor> processors;
}
