package hobbymatch.backend;

import hobbymatch.backend.seeders.HobbySeeder;
import hobbymatch.backend.seeders.UserHobbySeeder;
import hobbymatch.backend.seeders.UserSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataSeeder is a Spring component that runs automatically when the application starts.
 * It populates the database with initial sample data such as users, hobbies, and their relationships.
 * This is useful for development, testing, or demo purposes.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserSeeder userSeeder;
    private final HobbySeeder hobbySeeder;
    private final UserHobbySeeder userHobbySeeder;


    /**
     * The run method is called automatically by Spring Boot after the application starts.
     * It executes all the seeders in order: hobbies first, then users, then user-hobby relationships.
     * @param args optional command-line arguments (unused here)
     * @throws Exception if any seeding step fails
     */
    @Override
    public void run(String... args) throws Exception {
        hobbySeeder.seedHobbies();
        userSeeder.seedUsers();
        userHobbySeeder.seedUserHobbies();
    }
}
