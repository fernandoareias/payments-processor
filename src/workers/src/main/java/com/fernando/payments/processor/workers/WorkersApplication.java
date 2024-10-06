package com.fernando.payments.processor.workers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.fernando.payments.processor.core.domain"})
public class WorkersApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkersApplication.class, args);
	}

}
