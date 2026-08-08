package hobbymatch.backend.seeders;

import hobbymatch.backend.entities.Hobby;
import hobbymatch.backend.entities.User;
import hobbymatch.backend.repositories.HobbyRepository;
import hobbymatch.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds initial user-hobby relationships into the database.
 * This class assigns existing hobbies to specific users and ensures
 * the bidirectional relationship is properly maintained.
 * Example: Richard likes Soccer and Coding; Renata likes Running and Coding.
 */
@Component
@RequiredArgsConstructor
public class UserHobbySeeder {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;

    /**
     * Assigns hobbies to users and persists the relationships.
     * @Transactional ensures that all database operations are executed in a single transaction,
     * so either everything succeeds or everything rolls back on failure.
     * @SuppressWarnings("OptionalGetWithoutIsPresent") is used here because we are assuming
     * the users and hobbies already exist in the database for seeding purposes.
     */
    @Transactional
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void seedUserHobbies() {
        // Fetch users
        User richard = userRepository.findByUsername("richardmaliyetu").get();
        User renata = userRepository.findByUsername("renatamaliyetu").get();

        // Fetch hobbies
        Hobby soccer = hobbyRepository.findByName("Soccer").get();
        Hobby running = hobbyRepository.findByName("Running").get();
        Hobby coding = hobbyRepository.findByName("Coding").get();

        // Assign hobbies manually
        richard.getHobbies().add(soccer);
        richard.getHobbies().add(coding);

        renata.getHobbies().add(running);
        renata.getHobbies().add(coding);

        // Maintain bidirectional relationship
        soccer.getUsers().add(richard);
        running.getUsers().add(renata);
        coding.getUsers().add(richard);
        coding.getUsers().add(renata);

        // Save all users to persist the relationships
        userRepository.saveAll(List.of(richard, renata));
    }
}
