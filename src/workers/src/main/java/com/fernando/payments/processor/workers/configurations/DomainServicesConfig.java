package com.fernando.payments.processor.workers.configurations;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.core.domain.Processor;
import com.fernando.payments.processor.core.domain.interfaces.DomainServices;
import com.fernando.payments.processor.core.domain.services.ProcessCreditCardPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServicesConfig {

    @Bean
    public DomainServices<CreditCardPayment, Processor> processCreditCardPaymentService() {
        return new ProcessCreditCardPaymentService();
    }


}
