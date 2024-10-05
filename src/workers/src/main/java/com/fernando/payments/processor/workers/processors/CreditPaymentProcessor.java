package com.fernando.payments.processor.workers.processors;


import com.fernando.payments.processor.workers.dominio.creditcards.CreditCardPayment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditPaymentProcessor implements ItemProcessor<CreditCardPayment, CreditCardPayment> {


    private static final Logger log = LoggerFactory.getLogger(CreditPaymentProcessor.class);

    @Override
    public CreditCardPayment process(CreditCardPayment item) throws Exception {
        return null;
    }
}

