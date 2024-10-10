package com.fernando.payments.processor.workers.steps;

import com.fernando.payments.processor.core.domain.CreditCardPayment;
import com.fernando.payments.processor.workers.processors.CreditPaymentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CreditPaymentProcessorStepsConfig {

    @Qualifier("transactionManagerApp")
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public CreditPaymentProcessor processor() {
        return new CreditPaymentProcessor();
    }

    @Bean
    public Step creditPaymentProcessorSteps(JobRepository jobRepository,
                                            JdbcCursorItemReader<CreditCardPayment> reader,
                                            CreditPaymentProcessor processor,
                                            ItemWriter<CreditCardPayment> writer) {

        return new StepBuilder("creditPaymentProcessorSteps", jobRepository)
                .<CreditCardPayment, CreditCardPayment> chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(3)
                .build();
    }

}
