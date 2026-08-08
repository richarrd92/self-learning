package hobbymatch.backend.services.implementations;

import hobbymatch.backend.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * Service implementation for JWT (JSON Web Token) operations.
 * Handles generating tokens, extracting user IDs, and validating tokens.
 */
@Service
@RequiredArgsConstructor
public class JwtServiceImplementation implements JwtService {

    // Injected from application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    /**
     * Generates a JWT token for a given userId.
     * @param userId the ID of the user
     * @return a signed JWT token as a string
     * The token contains:
     *  - Subject: the userId
     *  - IssuedAt timestamp: when the token was created
     *  - Expiration timestamp: when the token expires
     *  - Signature: signed with the secret key to ensure authenticity
     */
    @Override
    public String generateToken(Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // Build JWT token with subject, timestamps, and signature
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extracts the userId from a given JWT token.
     * @param token the JWT token
     * @return userId as a Long
     * Parses the token claims and reads the subject field (userId).
     */
    @Override
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject()); // extract userId from subject
    }

    /**
     * Validates a JWT token.
     * @param token the JWT token
     * @return true if the token is valid (not expired, properly signed)
     * Throws exceptions if token is invalid or expired.
     */
    @Override
    public boolean validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
        return true; // valid token
    }

    /**
     * Helper method to create a signing key from the secret string.
     * @return Key object used for signing and verifying JWTs
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
