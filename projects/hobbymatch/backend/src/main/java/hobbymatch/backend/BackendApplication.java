package hobbymatch.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the HobbyMatch backend application.
 * This class bootstraps the Spring Boot application, starting the embedded web server
 * and initializing all beans, controllers, services, and configurations.
 */
@SpringBootApplication
public class BackendApplication {

    /**
     * Main method to launch the Spring Boot application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
