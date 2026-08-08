package hobbymatch.backend.services.implementations;

import hobbymatch.backend.entities.User;
import hobbymatch.backend.repositories.UserRepository;
import hobbymatch.backend.services.JwtUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation to load user details for JWT authentication.
 * Converts a User entity from the database into a Spring Security UserDetails object,
 * which is used for authentication and authorization.
 */
@Service
@RequiredArgsConstructor
public class JwtUserServiceImplementation implements JwtUserService {
    private final UserRepository userRepository;

    /**
     * Load a user by their ID for authentication.
     * @param userId the ID of the user to load
     * @return a UserDetails object used by Spring Security
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + userId));

        // Prepend "ROLE_" - Spring Security expects it
        String authority = "ROLE_" + user.getRole().name();

        // Build Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())                   // Username for authentication
                .password(user.getPassword())                   // Hashed passwo
                .authorities(authority)         // Roles/authorities
                .disabled(!user.isActive())           // Disable account if not active
                .build();
    }
}
