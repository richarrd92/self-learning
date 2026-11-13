package com.cooksys.ftd.social_media.services;

import com.cooksys.ftd.social_media.dtos.*;

import java.util.List;

public interface TweetService {
    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    List<HashtagDto> getTagsForTweet(Long tweetId);

    List<UserResponseDto> getMentionsFromTweet(Long id);

    TweetResponseDto deleteTweet(Long tweetId, CredentialsDto credentialsDto);

    TweetResponseDto getTweetById(Long tweetId);

    void likeTweet(Long tweetId, CredentialsDto credentialsDto);

    TweetResponseDto createReplyTweet(Long id, TweetRequestDto tweetRequestDto);

    TweetResponseDto createRepostTweet(Long id, CredentialsDto credentialsDto);

    List<UserResponseDto> getTweetLikers(Long id);

    ContextDto getTweetContext(Long tweetId);

    List<TweetResponseDto> getDirectReplies(Long tweetId);

    List<TweetResponseDto> getDirectReposts(Long tweetId);
}
