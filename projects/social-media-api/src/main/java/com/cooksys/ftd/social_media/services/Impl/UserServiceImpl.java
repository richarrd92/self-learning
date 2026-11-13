package com.cooksys.ftd.social_media.services.Impl;

import com.cooksys.ftd.social_media.dtos.CredentialsDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.dtos.UserRequestDto;
import com.cooksys.ftd.social_media.dtos.UserResponseDto;
import com.cooksys.ftd.social_media.entities.Tweet;
import com.cooksys.ftd.social_media.entities.User;
import com.cooksys.ftd.social_media.entities.embeddables.Credentials;
import com.cooksys.ftd.social_media.entities.embeddables.Profile;
import com.cooksys.ftd.social_media.exceptions.BadRequestException;
import com.cooksys.ftd.social_media.exceptions.NotAuthorizedException;
import com.cooksys.ftd.social_media.exceptions.NotFoundException;
import com.cooksys.ftd.social_media.mappers.*;
import com.cooksys.ftd.social_media.repositories.UserRepository;
import com.cooksys.ftd.social_media.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final CredentialsMapper credentialsMapper;
    private final TweetMapper tweetMapper;


    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entitiesToDtos(userRepository.findAllByDeletedFalse());
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByCredentialsUsernameAndDeletedFalseIgnoreCase(username);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // Validate credentials
        if (userRequestDto.getCredentials() == null
                || userRequestDto.getCredentials().getUsername() == null
                || userRequestDto.getCredentials().getUsername().isBlank()
                || userRequestDto.getCredentials().getPassword() == null
                || userRequestDto.getCredentials().getPassword().isBlank()) {
            throw new BadRequestException("Credentials are required and must include a username and password");
        }

        // Validate profile
        if (userRequestDto.getProfile() == null
                || userRequestDto.getProfile().getEmail() == null
                || userRequestDto.getProfile().getEmail().isBlank()) {
            throw new BadRequestException("Profile is required and must include an email");
        }

        String username = userRequestDto.getCredentials().getUsername();

        // Check if username is already taken by an active user
        if (userRepository.existsByCredentialsUsernameAndDeletedFalseIgnoreCase(username)) {
            throw new BadRequestException("Username is already taken");
        }

        // Check if a deleted user exists with these credentials
        User deletedUser = userRepository.findByCredentialsUsernameAndDeletedTrueIgnoreCase(username).orElse(null);
        if (deletedUser != null) {
            // Reactivate deleted user
            deletedUser.setDeleted(false);
            // Optionally update profile info
            deletedUser.setProfile(profileMapper.dtoToEntity(userRequestDto.getProfile()));
            userRepository.save(deletedUser);
            return userMapper.entityToDto(deletedUser);
        }

        // Create new user
        User newUser = userMapper.dtoToEntity(userRequestDto);
        userRepository.save(newUser);

        return userMapper.entityToDto(newUser);
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        return userMapper.entityToDto(foundUser);
    }

    @Override
    public UserResponseDto updateUserProfile(String username, UserRequestDto userRequestDto) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(userRequestDto.getCredentials());

        if (!foundUser.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Credentials don't match");
        }

        Profile requestProfile = profileMapper.dtoToEntity(userRequestDto.getProfile());

        if (requestProfile == null) {
            throw new BadRequestException("Profile can't be null");
        }

        String oldFirstName = foundUser.getProfile().getFirstName();
        String oldLastName = foundUser.getProfile().getLastName();
        String oldEmail = foundUser.getProfile().getEmail();
        String oldPhone = foundUser.getProfile().getPhone();

        if (requestProfile.getFirstName() == null) {
            requestProfile.setFirstName(oldFirstName);
        }

        if (requestProfile.getLastName() == null) {
            requestProfile.setLastName(oldLastName);
        }

        if (requestProfile.getEmail() == null) {
            requestProfile.setEmail(oldEmail);
        }

        if (requestProfile.getPhone() == null) {
            requestProfile.setPhone(oldPhone);
        }

        foundUser.setProfile(requestProfile);
        userRepository.save(foundUser);

        return userMapper.entityToDto(foundUser);
    }

    @Override
    public UserResponseDto deleteUserByUsername(String username, CredentialsDto credentialsDto) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null) {
            throw new NotFoundException("User doesn't exist");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(credentialsDto);

        if (!foundUser.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Credentials don't match");
        }

        foundUser.setDeleted(true);
        userRepository.save(foundUser);

        return userMapper.entityToDto(foundUser);
    }

    @Override
    public void followUser(String username, CredentialsDto credentialsDto) {
        User followedUser = userRepository.findByCredentialsUsername(username);

        if (followedUser == null || followedUser.isDeleted()) {
            throw new NotFoundException("Followed User doesn't exist or has been deleted");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(credentialsDto);
        User followingUser = userRepository.findByCredentialsUsername(requestCredentials.getUsername());

        if (followingUser == null || followingUser.isDeleted()) {
            throw new NotFoundException("Following User doesn't exist or has been deleted");
        }

        if (!followingUser.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Credentials don't match");
        }

        if (followingUser.getFollowing().contains(followedUser)) {
            throw new BadRequestException(followingUser.getCredentials().getUsername() + " is already following " + username);
        }

        followingUser.getFollowing().add(followedUser);
        followedUser.getFollowers().add(followingUser);

        userRepository.save(followingUser);
        userRepository.save(followedUser);
    }

    @Override
    public void unfollowUser(String username, CredentialsDto credentialsDto) {
        User followedUser = userRepository.findByCredentialsUsername(username);

        if (followedUser == null || followedUser.isDeleted()) {
            throw new NotFoundException("Followed User doesn't exist or has been deleted");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(credentialsDto);
        User followingUser = userRepository.findByCredentialsUsername(requestCredentials.getUsername());

        if (followingUser == null || followingUser.isDeleted()) {
            throw new NotFoundException("Following User doesn't exist or has been deleted");
        }

        if (!followingUser.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Credentials don't match");
        }

        if (!followingUser.getFollowing().contains(followedUser)) {
            throw new BadRequestException(followingUser.getCredentials().getUsername() + " is already not following " + username);
        }

        followingUser.getFollowing().remove(followedUser);
        followedUser.getFollowers().remove(followingUser);

        userRepository.save(followingUser);
        userRepository.save(followedUser);
    }

    @Override
    public List<UserResponseDto> getFollowersByUsername(String username) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        Set<User> followers = foundUser.getFollowers();
        List<UserResponseDto> result = new ArrayList<>();

        for (User follower : followers) {
            if (!follower.isDeleted()) {
                result.add(userMapper.entityToDto(follower));
            }
        }

        return result;
    }

    @Override
    public List<UserResponseDto> getFollowingByUsername(String username) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        Set<User> followings = foundUser.getFollowing();
        List<UserResponseDto> result = new ArrayList<>();

        for (User following : followings) {
            if (!following.isDeleted()) {
                result.add(userMapper.entityToDto(following));
            }
        }

        return result;
    }

    @Override
    public List<TweetResponseDto> getTweetsByUsername(String username) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        List<Tweet> tweets = new ArrayList<>();
        for (Tweet tweet : foundUser.getTweets()) {
            if (!tweet.isDeleted()) {
                tweets.add(tweet);
            }
        }

        tweets.sort(new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                return t2.getPosted().compareTo(t1.getPosted());
            }
        });

        List<TweetResponseDto> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            result.add(tweetMapper.entityToDto(tweet));
        }

        return result;
    }

    @Override
    public List<TweetResponseDto> getFeed(String username) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        Set<User> users = foundUser.getFollowing();
        users.add(foundUser);

        List<Tweet> allTweets = new ArrayList<>();

        for (User user : users) {
            List<Tweet> tweets = user.getTweets();
            for (Tweet tweet : tweets) {
                if (!tweet.isDeleted()) {
                    allTweets.add(tweet);
                }
            }
        }

        allTweets.sort(new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                return t2.getPosted().compareTo(t1.getPosted());
            }
        });

        List<TweetResponseDto> result = new ArrayList<>();
        for (Tweet tweet : allTweets) {
            result.add(tweetMapper.entityToDto(tweet));
        }

        return result;
    }

    @Override
    public List<TweetResponseDto> getMentions(String username) {
        User foundUser = userRepository.findByCredentialsUsername(username);

        if (foundUser == null || foundUser.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        List<Tweet> mentionedTweets = new ArrayList<>();
        for (Tweet tweet : foundUser.getMentionedIn()) {
            if (!tweet.isDeleted()) {
                mentionedTweets.add(tweet);
            }
        }

        mentionedTweets.sort(new Comparator<Tweet>() {
            public int compare(Tweet t1, Tweet t2) {
                return t2.getPosted().compareTo(t1.getPosted());
            }
        });

        List<TweetResponseDto> result = new ArrayList<>();
        for (Tweet tweet : mentionedTweets) {
            result.add(tweetMapper.entityToDto(tweet));
        }

        return result;
    }

}
