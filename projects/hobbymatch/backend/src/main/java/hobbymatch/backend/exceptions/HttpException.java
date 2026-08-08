package hobbymatch.backend.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Custom exception class to represent HTTP-related errors in the application.
 * Extends RuntimeException, so it is unchecked and can be thrown anywhere.
 */
@Getter
public class HttpException extends RuntimeException {
    private final int status;
    private final String errorCode;
    private final String message;
    private final LocalDateTime timestamp;

    /**
     * Constructor for HttpException.
     * @param status HTTP status code
     * @param errorCode Application-specific error code
     * @param message Error message describing the exception
     */
    public HttpException(int status, String errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}