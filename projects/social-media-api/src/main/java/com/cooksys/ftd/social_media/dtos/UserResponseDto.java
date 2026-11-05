package com.cooksys.ftd.social_media.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private String username;    // unique
    private ProfileDto profile; // nested
    private Timestamp joined;   // set once and never updated
}

