package com.cooksys.ftd.social_media.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {
    private String firstName; // optional
    private String lastName;  // optional
    private String email;     // required
    private String phone;     // optional
}
