package hobbymatch.backend.seeders;

import hobbymatch.backend.entities.embaddables.EmbeddedLocation;
import hobbymatch.backend.entities.User;
import hobbymatch.backend.enums.Gender;
import hobbymatch.backend.enums.Role;
import hobbymatch.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Seeds initial users into the database.
 * This class pre-populates the User table with default users for testing
 * or demo purposes. Each user is given personal details, role, gender,
 * location, and profile completion status.
 */
@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Creates and saves default users in the database.
     * Two example users are seeded: Richard and Renata.
     */
    public void seedUsers() {

        // Create Richard
        User richard = new User();
        richard.setName("Richard Maliyetu");
        richard.setUsername("richardmaliyetu");
        richard.setPassword(passwordEncoder.encode("Password123"));
        richard.setBio("Hello, I'm Richard!");
        richard.setGender(Gender.MALE);
        richard.setDateOfBirth(LocalDate.of(1990, 5, 15));
        richard.setRole(Role.USER);
        EmbeddedLocation richardEmbeddedLocation = new EmbeddedLocation();
        richardEmbeddedLocation.setLocationName("New York, NY");
        richardEmbeddedLocation.setLatitude(40.7128);
        richardEmbeddedLocation.setLongitude(-74.0060);
        richard.setEmbeddedLocation(richardEmbeddedLocation);
        richard.setProfileComplete(true);

        // Create Renata
        User renata = new User();
        renata.setName("Renata Maliyetu");
        renata.setUsername("renatamaliyetu");
        renata.setPassword(passwordEncoder.encode("Password123"));
        renata.setBio("Hi, I'm Renata!");
        renata.setGender(Gender.FEMALE);
        renata.setDateOfBirth(LocalDate.of(1992, 8, 22));
        renata.setRole(Role.ADMIN);
        renata.setPublic(false);
        EmbeddedLocation renataEmbeddedLocation = new EmbeddedLocation();
        renataEmbeddedLocation.setLocationName("Los Angeles, CA");
        renataEmbeddedLocation.setLatitude(34.0522);
        renataEmbeddedLocation.setLongitude(-118.2437);
        renata.setEmbeddedLocation(renataEmbeddedLocation);
        renata.setProfileComplete(true);

        // Save all seeded users to the database
        userRepository.saveAll(List.of(richard, renata));
    }
}
