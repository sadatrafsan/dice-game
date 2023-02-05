package com.hishab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DiceGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}

}
