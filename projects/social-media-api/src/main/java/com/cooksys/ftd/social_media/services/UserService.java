package com.cooksys.ftd.social_media.services;

import com.cooksys.ftd.social_media.dtos.CredentialsDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.dtos.UserRequestDto;
import com.cooksys.ftd.social_media.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    boolean usernameExists(String username);

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserByUsername(String username);

    UserResponseDto updateUserProfile(String username, UserRequestDto userRequestDto);

    UserResponseDto deleteUserByUsername(String username, CredentialsDto credentialsDto);

    void followUser(String username, CredentialsDto credentialsDto);

    void unfollowUser(String username, CredentialsDto credentialsDto);

    List<UserResponseDto> getFollowersByUsername(String username);

    List<UserResponseDto> getFollowingByUsername(String username);

    List<TweetResponseDto> getTweetsByUsername(String username);

    List<TweetResponseDto> getFeed(String username);

    List<TweetResponseDto> getMentions(String username);
}
