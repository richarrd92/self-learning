package com.cooksys.ftd.social_media.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CredentialsDto {
    private String username;
    private String password; // plain text here
}
