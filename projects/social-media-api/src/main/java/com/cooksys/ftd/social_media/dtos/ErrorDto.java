package com.cooksys.ftd.social_media.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorDto {
    private String message;

    public ErrorDto(String message) {
        this.message = message;
    }
}
