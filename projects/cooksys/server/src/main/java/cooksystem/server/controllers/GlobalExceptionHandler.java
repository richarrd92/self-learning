package cooksystem.server.controllers;

import cooksystem.server.dtos.ErrorResponseDto;
import cooksystem.server.exceptions.AuthenticationException;
import cooksystem.server.exceptions.BadRequestException;
import cooksystem.server.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestExceptions(BadRequestException badRequestException){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                badRequestException.getStatus(),
                badRequestException.getErrorCode(),
                badRequestException.getDetails()
        );
        return ResponseEntity.status(badRequestException.getStatus()).body(errorResponseDto);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationExceptions(AuthenticationException authenticationException){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                authenticationException.getStatus(),
                authenticationException.getErrorCode(),
                authenticationException.getDetails()
        );
        return ResponseEntity.status(authenticationException.getStatus()).body(errorResponseDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundExceptions(NotFoundException notFoundException){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                notFoundException.getStatus(),
                notFoundException.getErrorCode(),
                notFoundException.getDetails()
        );
        return ResponseEntity.status(notFoundException.getStatus()).body(errorResponseDto);
    }

    // TODO: Add more exceptions?

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleOtherExceptions(Exception exception){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                500,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }
}
