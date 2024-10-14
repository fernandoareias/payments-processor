package com.fernando.payments.processor.workers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@EntityScan(basePackages = {"com.fernando.payments.processor.core.domain"})
public class WorkersApplication  implements CommandLineRunner {

	@Autowired
	private JobRunner jobRunner;

	public static void main(String[] args) {
		SpringApplication.run(WorkersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String jobNamesArg = Arrays.stream(args)
				.filter(arg -> arg.startsWith("--jobNames="))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("The parameter --jobNames= is required"));

		String jobNames = jobNamesArg.replace("--jobNames=", "");

		String[] jobs = jobNames.split(",");

		jobRunner.runJobsInParallel(jobs);
	}
}
