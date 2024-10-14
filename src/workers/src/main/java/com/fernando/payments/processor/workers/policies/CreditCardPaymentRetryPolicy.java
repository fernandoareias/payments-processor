package com.fernando.payments.processor.workers.policies;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.enums.PaymentStatus;
import com.fernando.payments.processor.workers.JobRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.classify.Classifier;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;

public class CreditCardPaymentRetryPolicy extends SimpleRetryPolicy {

    private static final Logger log = LoggerFactory.getLogger(CreditCardPaymentRetryPolicy.class);

    @Override
    public boolean canRetry(RetryContext context) {
        Throwable lastThrowable = context.getLastThrowable();

        if (lastThrowable instanceof Exception) {
            Object item = context.getAttribute("record");

            if (item instanceof CreditCardPayment) {
                CreditCardPayment payment = (CreditCardPayment) item;

                if (payment.getStatus() == PaymentStatus.FAILED) {
                    log.info(String.format("Retry payment: %s", payment.getAggregateId().toString()));
                    return true;
                }
            }
        }
        return false;
    }
}
