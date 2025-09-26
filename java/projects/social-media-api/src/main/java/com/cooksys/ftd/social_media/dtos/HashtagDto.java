package com.cooksys.ftd.social_media.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class HashtagDto {
    private String label;       // unique and case-insensitive
    private Timestamp firstUsed; // set once and immutable
    private Timestamp lastUsed;  // updated on new usage
}
