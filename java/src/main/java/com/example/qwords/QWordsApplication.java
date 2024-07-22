package com.example.qwords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.qwords"})
public class QWordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QWordsApplication.class, args);
	}

}
