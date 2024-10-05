package com.fernando.payments.processor.workers.dominio.creditcards.interfaces;

import com.fernando.payments.processor.workers.dominio.creditcards.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, Long> {
}
