package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication
public class AccountYunkiMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountYunkiMicroserviceApplication.class, args);
	}

}
