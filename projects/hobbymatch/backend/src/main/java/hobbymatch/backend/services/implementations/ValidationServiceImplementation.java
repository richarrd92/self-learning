package hobbymatch.backend.services.implementations;

import hobbymatch.backend.repositories.UserRepository;
import hobbymatch.backend.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for validating user input and profile completeness.
 * Checks things like username format, name format, password strength, and full profile completion.
 */
@Service
@RequiredArgsConstructor
public class ValidationServiceImplementation implements ValidationService {
    private final UserRepository userRepository;

    /**
     * Check if a username is valid.
     * Rules:
     * 1. Cannot be null or blank
     * 2. Must be alphanumeric (letters + numbers only)
     * 3. Must be unique in the database
     * @param username the username to validate
     * @return true if invalid, false if valid
     */
    @Override
    public boolean isUsernameValid(String username) {
        // Null/blank check + alphanumeric only + uniqueness
        if (username == null || username.isBlank()) return true;
        if (!username.matches("^[a-zA-Z0-9]+$")) return true;
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Check if a name is valid.
     * Rules:
     * 1. Allows letters, spaces, apostrophes, and hyphens
     * 2. Length must be 2–50 characters
     * @param name the name to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isNameValid(String name) {
        // Allows letters, spaces, apostrophes, hyphens: 2–50 chars total
        return name != null && name.matches("^[a-zA-Z][a-zA-Z\\s'-]{1,49}$");
    }


    /**
     * Check if a password is valid.
     * Rules:
     * 1. Must be at least 8 characters
     * 2. Must contain at least 1 letter
     * 3. Must contain at least 1 number
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isPasswordValid(String password) {
        // Null check + min 8 chars, at least 1 letter and 1 number
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
