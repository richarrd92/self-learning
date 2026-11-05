package com.cooksys.ftd.social_media.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private CredentialsDto credentials; // username + password
    private ProfileDto profile;         // required: email
}
