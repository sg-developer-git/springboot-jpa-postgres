package com.sg.poc.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.sg.poc.postgres,com.sg.poc.utility"})
@SpringBootApplication
public class PostgresPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgresPocApplication.class, args);
	}

}
