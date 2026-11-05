package cooksystem.server.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final int status;
    private final String errorCode;
    private final String details;

    /**
     * Constructs a NotFoundException with a simple message.
     * Sets default status to 404 and errorCode to "NOT_FOUND"
     */
    public NotFoundException(String message) {
        super(message);
        this.status = 404;
        this.errorCode = "NOT_FOUND";
        this.details = message;
    }

    /**
     * Constructs a NotFoundException with full details.
     * Allows specifying a custom errorCode and detailed description.
     */
    public NotFoundException(String message, String errorCode, String details) {
        super(message);
        this.status = 404;
        this.errorCode = errorCode;
        this.details = details;
    }
}
