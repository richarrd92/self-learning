package com.cooksys.ftd.social_media.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 8123456789012345678L;

    public BadRequestException(String message) {
        super(message);
    }}
