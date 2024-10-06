package com.fernando.payments.processor.workers.processors;


import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.Processor;
import com.fernando.payments.processor.core.domain.interfaces.DomainServices;
import com.fernando.payments.processor.core.domain.services.ProcessCreditCardPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditPaymentProcessor implements ItemProcessor<CreditCardPayment, CreditCardPayment> {
    private static final Logger log = LoggerFactory.getLogger(CreditPaymentProcessor.class);

    @Autowired
    private DomainServices<CreditCardPayment, Processor> processCreditCardPaymentService;

    @Override
    public CreditCardPayment process(CreditCardPayment item) throws Exception {

        item.ProcessPayment(processCreditCardPaymentService);

        return item;
    }
}

