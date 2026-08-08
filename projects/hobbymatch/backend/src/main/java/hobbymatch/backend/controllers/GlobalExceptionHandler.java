package hobbymatch.backend.controllers;

import hobbymatch.backend.dtos.ExceptionDto;
import hobbymatch.backend.exceptions.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * Catches exceptions thrown by controllers and returns a structured
 * error response (ExceptionDto) with HTTP status codes, error codes,
 * messages, and timestamps.
 * Using @ControllerAdvice allows centralized exception handling for all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom HttpException instances.
     * @param httpException a custom exception containing status, code, and message
     * @return ResponseEntity with structured ExceptionDto and the appropriate HTTP status
     * Example use case: Throwing a 404 NOT_FOUND when a user is not found.
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ExceptionDto> handleHttpExceptions(HttpException httpException){
        ExceptionDto exceptionDto = new ExceptionDto(
                httpException.getStatus(),
                httpException.getErrorCode(),
                httpException.getMessage(),
                httpException.getTimestamp()
        );
        return ResponseEntity.status(httpException.getStatus()).body(exceptionDto);
    }

    /**
     * Handles all other unexpected exceptions not specifically caught elsewhere.
     * @param exception any unhandled exception
     * @return ResponseEntity with a generic 500 INTERNAL_SERVER_ERROR response
     * This ensures that the API never exposes stack traces or internal errors directly.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception exception) {
        ExceptionDto exceptionDto = new ExceptionDto(
                500,                                    // HTTP 500 Internal Server Error
                "INTERNAL_SERVER_ERROR",                        // Generic error code
                "An unexpected error occurred",                 // Generic message
                java.time.LocalDateTime.now()                   // Current timestamp
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDto);
    }
}
