package com.cooksys.ftd.social_media.controllers;

import com.cooksys.ftd.social_media.dtos.*;
import com.cooksys.ftd.social_media.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> getAllTweets(){
        return tweetService.getAllTweets();
    }

    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(tweetRequestDto);
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable Long id) {
        return tweetService.getTweetById(id);
    }

    @PostMapping("/{id}/like")
    public void likeTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        tweetService.likeTweet(id, credentialsDto);
    }

    @GetMapping("/{id}/tags")
    public List<HashtagDto> getTagsForTweet(@PathVariable Long id) {
        return tweetService.getTagsForTweet(id);
    }

    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getMentionsFromTweet(@PathVariable Long id) {
        return tweetService.getMentionsFromTweet(id);
    }

    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.deleteTweet(id, credentialsDto);
    }

    @PostMapping("/{id}/reply")
    public TweetResponseDto createReplyTweet(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createReplyTweet(id, tweetRequestDto);
    }

    @GetMapping("/{id}/replies")
    public List<TweetResponseDto> getReplies(@PathVariable Long id) {
        return tweetService.getDirectReplies(id);
    }

    @PostMapping("/{id}/repost")
    public TweetResponseDto createRepostTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.createRepostTweet(id, credentialsDto);
    }

    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getReposts(@PathVariable Long id) {
        return tweetService.getDirectReposts(id);
    }

    @GetMapping("/{id}/likes")
    public List<UserResponseDto> getTweetLikers(@PathVariable Long id) {
        return tweetService.getTweetLikers(id);
    }

    @GetMapping("/{id}/context")
    public ContextDto getTweetContext(@PathVariable Long id) {
        return tweetService.getTweetContext(id);
    }
}
