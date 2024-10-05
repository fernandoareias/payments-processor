package com.fernando.payments.processor.webapi.domain.creditcards.interfaces;

import com.fernando.payments.processor.webapi.domain.creditcards.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, Long> {
}
