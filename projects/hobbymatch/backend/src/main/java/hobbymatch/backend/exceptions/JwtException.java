package hobbymatch.backend.exceptions;

/**
 * Custom exception class for JWT (JSON Web Token) related errors.
 * Extends HttpException to provide HTTP-specific information (status code, error code, message, timestamp).
 * Used whenever there is an issue with authorization tokens.
 */
public class JwtException extends HttpException {
    private static final int STATUS = 401;
    private static final String CODE = "INVALID_JWT";

    /**
     * Constructor for JwtException
     * @param message A descriptive error message
     */
    public JwtException(String message) {
        super(STATUS, CODE, message);
    }

    public static JwtException missingToken() {
        return new JwtException("Authorization token is missing");
    }

    public static JwtException invalidOrExpired() {
        return new JwtException("JWT token is invalid or expired");
    }
}
