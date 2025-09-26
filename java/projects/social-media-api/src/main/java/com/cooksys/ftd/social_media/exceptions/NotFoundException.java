package com.cooksys.ftd.social_media.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7345678901234567890L;

    public NotFoundException(String message) {
        super(message);
    }
}
