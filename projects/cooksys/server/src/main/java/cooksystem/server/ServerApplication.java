package cooksystem.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
    // App running on http://localhost:8080
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
