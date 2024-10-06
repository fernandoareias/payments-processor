package com.fernando.payments.processor.core.domain;

import com.fernando.payments.processor.core.domain.comon.Entity;
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


}
