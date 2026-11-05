package com.example.challengesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengesAppApplication.class, args);

        System.out.println("SPRING_DATASOURCE_URL=" + System.getenv("SPRING_DATASOURCE_URL"));
        System.out.println("SPRING_DATASOURCE_USERNAME=" + System.getenv("SPRING_DATASOURCE_USERNAME"));
        System.out.println("SPRING_DATASOURCE_PASSWORD EMPTY? " +
                (System.getenv("SPRING_DATASOURCE_PASSWORD") == null));
	}

}
