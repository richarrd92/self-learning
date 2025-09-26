package com.cooksys.ftd.social_media.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TweetRequestDto {
    private CredentialsDto credentials; // author’s identity
    private String content;             // optional
    private Long inReplyToId;           // optional
    private Long repostOfId;            // optional
}
