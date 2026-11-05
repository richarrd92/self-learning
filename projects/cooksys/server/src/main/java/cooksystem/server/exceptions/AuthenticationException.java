package cooksystem.server.exceptions;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException{
    private final int status;
    private final String errorCode;
    private final String details;

    /**
     * Constructs an AuthenticationException with a simple message.
     * Sets default status to 401 and errorCode to "AUTH_FAILED"
     * */
    public AuthenticationException(String message){
        super(message);
        this.status = 400;
        this.errorCode = "AUTH_FAILED";
        this.details = message;
    }

    /**
     * Constructs an AuthenticationException with full details.
     * Allows specifying a custom errorCode and detailed description.
     * */
    public AuthenticationException(String message, String errorCode, String details){
        super(message);
        this.status = 401;
        this.errorCode = errorCode;
        this.details = details;
    }
}
