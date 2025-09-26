package com.cooksys.ftd.social_media.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class TweetResponseDto {
    private Long id;
    private UserResponseDto author;
    private Timestamp posted; // set once and immutable
    private String content;   // optional
    private TweetResponseDto inReplyTo; // optional
    private TweetResponseDto repostOf;  // optional
}
