package hobbymatch.backend.services;

/**
 * Service interface for handling JWT (JSON Web Token) operations.
 * Provides methods for generating, validating, and extracting info from tokens.
 */
public interface JwtService {
    String generateToken(Long userId);
    Long getUserIdFromToken(String token);
    boolean validateToken(String token);
}
