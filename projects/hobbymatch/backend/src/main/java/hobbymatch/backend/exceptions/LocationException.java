package hobbymatch.backend.exceptions;

/**
 * Custom exception class for Location-related errors.
 * Extends HttpException to provide HTTP-specific information (status code, error code, message, timestamp).
 * Used whenever there is an issue related to location operations, such as not found or invalid input.
 */
public class LocationException extends HttpException{

    /**
     * Constructor for LocationException
     * @param status HTTP status code (e.g., 400, 404)
     * @param errorCode Application-specific error code
     * @param message Descriptive error message
     */
    public LocationException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }

    public static LocationException notFound(String message) {
        return new LocationException(404, "LOCATION_NOT_FOUND", message);
    }

    public static LocationException invalidInput(String message) {
        return new LocationException(400, "INVALID_INPUT", message);
    }
}
