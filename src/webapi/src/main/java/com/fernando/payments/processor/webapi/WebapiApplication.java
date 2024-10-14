package com.fernando.payments.processor.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.fernando.payments.processor.core.domain"})
public class WebapiApplication{

	public static void main(String[] args) {
		SpringApplication.run(WebapiApplication.class, args);
	}
}
