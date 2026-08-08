package hobbymatch.backend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Service interface for loading user details specifically for JWT authentication.
 * Provides a method to load a user by their ID for Spring Security context.
 */
public interface JwtUserService {
    UserDetails loadUserById(Long userId) throws UsernameNotFoundException;
}
