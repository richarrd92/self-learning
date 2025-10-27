package cooksystem.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private final int status;
    private final String errorCode;
    private final String details;
}
