package com.fernando.payments.processor.workers.writers;

import com.fernando.payments.processor.workers.dominio.creditcards.CreditCardPayment;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcCreditPaymentProcessorWriterConfig {
    @Bean
    public ItemWriter<CreditCardPayment> jdbcCursorWriter() {
        System.out.println("ESCREVEU");
        return clientes -> clientes.forEach(System.out::println);
    }
}
