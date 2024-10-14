package com.fernando.payments.processor.webapi.infrastructure;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, long> {
}
