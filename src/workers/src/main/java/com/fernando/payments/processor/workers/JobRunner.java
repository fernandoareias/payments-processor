package com.fernando.payments.processor.workers;

import com.fernando.payments.processor.workers.processors.CreditPaymentProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class JobRunner {
    private static final Logger log = LoggerFactory.getLogger(JobRunner.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext applicationContext;

    public void runJobsInParallel(String[] jobNames) throws Exception {
        List<String> jobsToRun = Arrays.asList(jobNames);
        log.info("Starting jobs: {}", jobsToRun);
        ExecutorService executorService = Executors.newFixedThreadPool(jobsToRun.size());



        try {
            for (String jobName : jobsToRun) {
                executorService.submit(() -> {
                    try {
                        log.info("Try running job: {}", jobName);
                        Job job = applicationContext.getBean(jobName, Job.class);

                        if(job == null)
                        {
                            log.error(String.format("Job not found: %s", jobName));
                            return;
                        }

                        log.info(String.format("Running job: %s", jobName));

                        JobParameters jobParameters = new JobParametersBuilder()
                                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                                .toJobParameters();

                        jobLauncher.run(job, jobParameters);
                    } catch (Exception e) {
                        log.error(String.format("Error while executing the job: %s", jobName));
                        e.printStackTrace();
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
    }
}
