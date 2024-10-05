package com.fernando.payments.processor.workers.steps;

import com.fernando.payments.processor.workers.dominio.creditcards.CreditCardPayment;
import com.fernando.payments.processor.workers.processors.CreditPaymentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class CreditPaymentProcessorStepsConfig {

    @Bean
    public Step creditPaymentProcessorSteps(JobRepository jobRepository,
                                            DataSourceTransactionManager transactionManager,
                                            JdbcCursorItemReader<CreditCardPayment> reader,
                                            CreditPaymentProcessor processor,
                                            ItemWriter<CreditCardPayment> writer) {

        return new StepBuilder("creditPaymentProcessorSteps", jobRepository)
                .<CreditCardPayment, CreditCardPayment> chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .build();
    }

}
