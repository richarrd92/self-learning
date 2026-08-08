package hobbymatch.backend.seeders;

import hobbymatch.backend.entities.Hobby;
import hobbymatch.backend.enums.Category;
import hobbymatch.backend.repositories.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds initial hobby data into the database.
 * This class is used to pre-populate the Hobby table with some default entries
 * (e.g., Soccer, Running, Coding) when the application starts or for testing purposes.
 */
@Component
@RequiredArgsConstructor
public class HobbySeeder {
    private final HobbyRepository hobbyRepository;

    // Creates and saves a set of default hobbies.
    public void seedHobbies() {

        // Create Soccer
        Hobby soccer = new Hobby();
        soccer.setName("Soccer");
        soccer.setCategory(Category.SPORTS);

        // Create Running
        Hobby running = new Hobby();
        running.setName("Running");
        running.setCategory(Category.SPORTS);

        // Create Coding
        Hobby coding = new Hobby();
        coding.setName("Coding");
        coding.setCategory(Category.TECHNOLOGY);

        // Save all seeded hobbies to the database
        hobbyRepository.saveAll(List.of(soccer, running, coding));
    }
}
