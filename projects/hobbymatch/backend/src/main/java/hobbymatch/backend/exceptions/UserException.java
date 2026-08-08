package hobbymatch.backend.exceptions;

/**
 * Custom exception class for User-related errors.
 * Extends HttpException to provide HTTP-specific information (status code, error code, message, timestamp).
 * Used whenever there is an issue related to user operations, such as not found, invalid input, or inactive account.
 */
public class UserException extends HttpException {

    /**
     * Constructor for UserException
     * @param status HTTP status code (e.g., 400, 403, 404)
     * @param errorCode Application-specific error code
     * @param message Descriptive error message
     */
    public UserException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }

    public static UserException notFound(String message) {
        return new UserException(404, "USER_NOT_FOUND", message);
    }

    public static UserException duplicateUsername(String message) {
        return new UserException(400, "DUPLICATE_USERNAME", message);
    }

    public static UserException invalidInput(String message) {
        return new UserException(400, "INVALID_INPUT", message);
    }

    public static UserException userInactive() {
        return new UserException(403, "USER_INACTIVE", "User account is deactivated");
    }
}
