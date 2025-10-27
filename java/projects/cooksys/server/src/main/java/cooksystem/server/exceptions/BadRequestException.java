package cooksystem.server.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{
    private final int status;
    private final String errorCode;
    private final String details;

    /**
     * Constructs a BadRequestException with a simple message.
     * Sets default status to 400 and errorCode to "BAD_REQUEST"
     * */
    public BadRequestException(String message){
        super(message);
        this.status = 400;
        this.errorCode = "BAD_REQUEST";
        this.details = message;
    }

    /**
     * Constructs a BadRequestException with full details.
     * Allows specifying a custom errorCode and detailed description.
     * */
    public BadRequestException(String message, String errorCode, String details){
        super(message);
        this.status = 400;
        this.errorCode = errorCode;
        this.details = details;
    }
}
