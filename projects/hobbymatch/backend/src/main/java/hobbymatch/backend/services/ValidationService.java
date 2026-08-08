package hobbymatch.backend.services;

/**
 * Service interface for validating user data and profile completeness.
 * Provides methods to check usernames, names, passwords, and profile fields.
 */
public interface ValidationService {
    boolean isUsernameValid(String username);
    boolean isNameValid(String name);
    boolean isPasswordValid(String password);
}
