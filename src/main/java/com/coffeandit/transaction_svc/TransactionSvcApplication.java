package com.coffeandit.transaction_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class TransactionSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionSvcApplication.class, args);
	}

}
