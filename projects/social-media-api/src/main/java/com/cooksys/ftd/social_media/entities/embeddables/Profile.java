package com.cooksys.ftd.social_media.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Profile embedded class
@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Profile {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
