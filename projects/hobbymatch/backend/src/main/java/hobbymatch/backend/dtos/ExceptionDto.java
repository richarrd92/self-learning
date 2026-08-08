package hobbymatch.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * DTO for sending structured error responses to clients.
 * Encapsulates HTTP status, error code, message, and timestamp.
 */
@AllArgsConstructor
@Getter
public class ExceptionDto {
    private int status;
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;
}
