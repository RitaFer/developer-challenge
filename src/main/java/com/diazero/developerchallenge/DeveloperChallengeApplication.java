package com.diazero.developerchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DeveloperChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeveloperChallengeApplication.class, args);
	}
}