package com.fernando.payments.processor.workers.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditPaymentProcessorJobConfig {

    public Job creditPaymentProcessorJob(JobRepository jobRepository,
                                         Step step1){
        return new JobBuilder("creditPaymentProcessorJob", jobRepository)
                .start(step1)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
