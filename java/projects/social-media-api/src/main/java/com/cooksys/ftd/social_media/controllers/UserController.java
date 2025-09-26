package com.cooksys.ftd.social_media.controllers;

import com.cooksys.ftd.social_media.dtos.CredentialsDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.dtos.UserRequestDto;
import com.cooksys.ftd.social_media.dtos.UserResponseDto;
import com.cooksys.ftd.social_media.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    // Retrieves a user with the given username.
    // If no such user exists or is deleted, an error should be sent in lieu of a response.
    @GetMapping("/@{username}")
    public UserResponseDto getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Updates the profile of a user with the given username.
    // If no such user exists, the user is deleted, or the provided credentials do not match the user, an error should be sent in lieu of a response.
    // In the case of a successful update, the returned user should contain the updated data.
    @PatchMapping("/@{username}")
    public UserResponseDto updateUserProfile(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUserProfile(username, userRequestDto);
    }

    // Deletes a user with the given username.
    // If no such user exists or the provided credentials do not match the user, an error should be sent in lieu of a response.
    // If a user is successfully "deleted", the response should contain the user data prior to deletion.
    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        return userService.deleteUserByUsername(username, credentialsDto);
    }

    @PostMapping("/@{username}/follow")
    public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.followUser(username, credentialsDto);
    }

    @PostMapping("/@{username}/unfollow")
    public void unfollowUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.unfollowUser(username, credentialsDto);
    }

    @GetMapping("/@{username}/followers")
    public List<UserResponseDto> getFollowers(@PathVariable String username) {
        return userService.getFollowersByUsername(username);
    }

    @GetMapping("/@{username}/following")
    public List<UserResponseDto> getFollowing(@PathVariable String username) {
        return userService.getFollowingByUsername(username);
    }

    @GetMapping("/@{username}/tweets")
    public List<TweetResponseDto> getTweetsByUser(@PathVariable String username) {
        return userService.getTweetsByUsername(username);
    }

    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto> getFeed(@PathVariable String username) {
        return userService.getFeed(username);
    }

    @GetMapping("/@{username}/mentions")
    public List<TweetResponseDto> getMentions(@PathVariable String username) {
        return userService.getMentions(username);
    }

}
