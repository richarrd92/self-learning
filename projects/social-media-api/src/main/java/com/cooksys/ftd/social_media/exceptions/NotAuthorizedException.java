package com.cooksys.ftd.social_media.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 8456789012345678901L;

    public NotAuthorizedException(String message) {
        super(message);
    }
}
