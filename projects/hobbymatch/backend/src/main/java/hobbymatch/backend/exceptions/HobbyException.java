package hobbymatch.backend.exceptions;

/**
 * Custom exception class for Hobby-related errors.
 * Extends HttpException to provide HTTP-specific information (status code, error code, message, timestamp).
 * Used whenever there is an issue related to hobby operations, such as not found, duplicate, or invalid input.
 */
public class HobbyException extends HttpException{

    /**
     * Constructor for HobbyException
     * @param status HTTP status code (e.g., 400, 404)
     * @param errorCode Application-specific error code
     * @param message Descriptive error message
     */
    public HobbyException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }

    public static HobbyException notFound(String message) {
        return new HobbyException(404, "HOBBY_NOT_FOUND", message);
    }

    public static HobbyException duplicateName(String message) {
        return new HobbyException(400, "DUPLICATE_HOBBY_NAME", message);
    }

    public static HobbyException invalidInput(String message) {
        return new HobbyException(400, "INVALID_INPUT", message);
    }
}
