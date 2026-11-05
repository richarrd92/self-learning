package com.cooksys.ftd.social_media.services.Impl;

import com.cooksys.ftd.social_media.dtos.*;
import com.cooksys.ftd.social_media.entities.Hashtag;
import com.cooksys.ftd.social_media.entities.Tweet;
import com.cooksys.ftd.social_media.entities.User;
import com.cooksys.ftd.social_media.entities.embeddables.Credentials;
import com.cooksys.ftd.social_media.exceptions.BadRequestException;
import com.cooksys.ftd.social_media.exceptions.NotAuthorizedException;
import com.cooksys.ftd.social_media.exceptions.NotFoundException;
import com.cooksys.ftd.social_media.mappers.*;
import com.cooksys.ftd.social_media.repositories.HashtagRepository;
import com.cooksys.ftd.social_media.repositories.TweetRepository;
import com.cooksys.ftd.social_media.repositories.UserRepository;
import com.cooksys.ftd.social_media.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final TweetMapper tweetMapper;
    private final CredentialsMapper credentialsMapper;
    private final HashtagMapper hashtagMapper;
    private final UserMapper userMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {
        return tweetMapper.entitiesToDtos(tweetRepository.findAllByDeletedFalse());
    }

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        if (tweetRequestDto.getCredentials() == null) {
            throw new BadRequestException("Credentials are required");
        }

        String username = tweetRequestDto.getCredentials().getUsername();

        // Find the User
        User author = userRepository.findByCredentialsUsername(username);

        if (author == null || author.isDeleted()) {
            throw new NotFoundException("Author doesn't exist or has been deleted");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(tweetRequestDto.getCredentials());

        // Validating credentials
        if (!author.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Valid credentials are required.");
        }

        // Checking if content exists
        if (tweetRequestDto.getContent() == null || tweetRequestDto.getContent().isBlank()) {
            throw new BadRequestException("Content is required for a tweet.");
        }

        // Create tweet
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequestDto.getContent());
        tweet.setAuthor(author);
        author.getTweets().add(tweet);

        // Save to database
        tweetRepository.saveAndFlush(tweet);

        // Process mentions and hashtags
        processMentionsAndHashtags(tweet);

        // Save to database again
        tweetRepository.save(tweet);

        // Convert to response DTO and return
        return tweetMapper.entityToDto(tweet);
    }

    @Override
    public TweetResponseDto createReplyTweet(Long id, TweetRequestDto tweetRequestDto) {
        if (tweetRequestDto.getCredentials() == null) {
            throw new BadRequestException("Credentials are required");
        }

        String username = tweetRequestDto.getCredentials().getUsername();
        User author = userRepository.findByCredentialsUsername(username);

        if (author == null || author.isDeleted()) {
            throw new NotFoundException("Author doesn't exist or has been deleted");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(tweetRequestDto.getCredentials());

        // Validating credentials
        if (!author.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Valid credentials are required.");
        }
        // Checking if content exists
        if (tweetRequestDto.getContent() == null || tweetRequestDto.getContent().isBlank()) {
            throw new BadRequestException("Content is required for a tweet.");
        }

        // --------------------------------------------------------

        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet doesn't exist");
        }
        Tweet repliedTweet = optionalTweet.get();
        if (repliedTweet.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        // Create reply tweet
        Tweet replyTweet = new Tweet();
        replyTweet.setContent(tweetRequestDto.getContent());
        replyTweet.setAuthor(author);
        replyTweet.setInReplyTo(repliedTweet);
        author.getTweets().add(replyTweet);

        // Save to database
        tweetRepository.saveAndFlush(replyTweet);

        // Process mentions and hashtags
        processMentionsAndHashtags(replyTweet);

        // Save to database again
        tweetRepository.save(replyTweet);

        // Convert to response DTO and return
        return tweetMapper.entityToDto(replyTweet);
    }

    @Override
    public TweetResponseDto createRepostTweet(Long id, CredentialsDto credentialsDto) {
        if (credentialsDto == null) {
            throw new BadRequestException("Credentials are required");
        }

        User author = userRepository.findByCredentialsUsername(credentialsDto.getUsername());

        if (author == null || author.isDeleted()) {
            throw new NotFoundException("Author doesn't exist or has been deleted");
        }

        Credentials requestCredentials = credentialsMapper.dtoToEntity(credentialsDto);

        // Validating credentials
        if (!author.getCredentials().equals(requestCredentials)) {
            throw new NotAuthorizedException("Valid credentials are required.");
        }

        // --------------------------------------------------------

        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet doesn't exist");
        }
        Tweet ogTweet = optionalTweet.get();
        if (ogTweet.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        // Create repost tweet
        Tweet repostTweet = new Tweet();
        repostTweet.setAuthor(author);
        repostTweet.setRepostOf(ogTweet);
        author.getTweets().add(repostTweet);

        // Save to database
        tweetRepository.save(repostTweet);

        // Convert to response DTO and return
        return tweetMapper.entityToDto(repostTweet);
    }

    @Override
    public List<UserResponseDto> getTweetLikers(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet doesn't exist");
        }
        Tweet tweet = optionalTweet.get();

        if (tweet.isDeleted()) {
            throw  new NotFoundException("Tweet has been deleted");
        }

        List<User> result = new ArrayList<>();
        for (User user : tweet.getLikedBy()) {
            if (!user.isDeleted()) {
                result.add(user);
            }
        }

        return userMapper.entitiesToDtos(result);
    }

    // Get the full context of a tweet
    @Override
    public ContextDto getTweetContext(Long tweetId) {
        // Retrieve the target tweet from the database
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        Tweet target = optionalTweet.get();

        if (target.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        // Build the BEFORE chain (all parent tweets / ancestors)
        List<Tweet> beforeChain = new ArrayList<>();
        Tweet current = target.getInReplyTo();

        // Traverse up the reply chain until there are no more parents
        while (current != null) {
            // Only include non-deleted tweets
            if (!current.isDeleted()) {
                beforeChain.add(current);
            }

            // Move up one level in the chain
            current = current.getInReplyTo();
        }

        // Reverse the list so ancestors are in chronological order (oldest → newest)
        Collections.reverse(beforeChain);

        // Build the AFTER chain (all replies and replies-of-replies)
        List<Tweet> afterChain = new ArrayList<>();
        collectReplies(target, afterChain); // Use BFS helper to traverse descendants

        // Sort all replies chronologically by posted timestamp
        afterChain.sort(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet t1, Tweet t2) {
                return t1.getPosted().compareTo(t2.getPosted());
            }
        });

        // 4. Map to DTOs
        ContextDto context = new ContextDto();
        context.setTarget(tweetMapper.entityToDto(target));
        context.setBefore(tweetMapper.entitiesToDtos(beforeChain));
        context.setAfter(tweetMapper.entitiesToDtos(afterChain));

        // Return the full context
        return context;
    }

    @Override
    public List<TweetResponseDto> getDirectReplies(Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        Tweet tweet = optionalTweet.get();

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        List<Tweet> replies = tweet.getReplies();

        // Remove any replies that are deleted
        List<Tweet> nonDeletedReplies = new ArrayList<>();
        for (Tweet reply : replies) {
            if (!reply.isDeleted()) {
                nonDeletedReplies.add(reply);
            }
        }

        return tweetMapper.entitiesToDtos(nonDeletedReplies);
    }

    @Override
    public List<TweetResponseDto> getDirectReposts(Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        Tweet tweet = optionalTweet.get();
        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        // Get all direct reposts and filter out deleted ones
        List<Tweet> nonDeletedReposts = new ArrayList<>();
        for (Tweet repost : tweet.getReposts()) {
            if (!repost.isDeleted()) {
                nonDeletedReposts.add(repost);
            }
        }

        return tweetMapper.entitiesToDtos(nonDeletedReposts);
    }

    // Helper method to collect all replies of a tweet iteratively using BFS.
    private void collectReplies(Tweet parentTweet, List<Tweet> replyList) {
        // Initialize a queue with the parentTweet's direct replies
        Queue<Tweet> queue = new LinkedList<>();
        queue.addAll(parentTweet.getReplies()); // start with direct replies

        // Continue until all replies and nested replies are processed
        while (!queue.isEmpty()) {

            // get one reply from the queue
            Tweet current = queue.poll();

            // Add to replyList if the tweet is not deleted
            if (!current.isDeleted()) {
                replyList.add(current);
            }

            // Add all children replies of the current tweet to the queue
            // This ensures we process replies-of-replies iteratively
            queue.addAll(current.getReplies());
        }
    }

    @Override
    public List<HashtagDto> getTagsForTweet(Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(tweetId);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found or deleted");
        }

        Tweet tweet = optionalTweet.get();

        // Convert Set -> List
        List<Hashtag> hashtags = new ArrayList<>(tweet.getHashtags());

        return hashtagMapper.entitiesToDtos(hashtags);

    }

    @Override
    public List<UserResponseDto> getMentionsFromTweet(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if (optionalTweet.isEmpty() || optionalTweet.get().isDeleted()) {
            throw new NotFoundException("Tweet not found or is deleted");
        }

        Tweet tweet = optionalTweet.get();

        // filter out deleted users
        List<User> mentions = new ArrayList<>();
        for (User user : tweet.getMentions()) {
            if (!user.isDeleted()) {
                mentions.add(user);
            }
        }

        return userMapper.entitiesToDtos(mentions);
    }

    @Override
    public TweetResponseDto deleteTweet(Long tweetId, CredentialsDto credentialsDto) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        Tweet tweet = optionalTweet.get();

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet already deleted");
        }

        // Validate credentials
        if (credentialsDto == null || credentialsDto.getUsername() == null || credentialsDto.getPassword() == null) {
            throw new BadRequestException("Credentials are required");
        }

        // Check if credentials match tweet author
        if (!tweet.getAuthor().getCredentials().getUsername().equals(credentialsDto.getUsername()) ||
                !tweet.getAuthor().getCredentials().getPassword().equals(credentialsDto.getPassword())) {
            throw new NotAuthorizedException("Credentials do not match tweet author");
        }

        // Soft-delete
        tweet.setDeleted(true);
        tweetRepository.save(tweet);

        return tweetMapper.entityToDto(tweet);
    }

    @Override
    public TweetResponseDto getTweetById(Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        Tweet tweet = optionalTweet.get();

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        return tweetMapper.entityToDto(tweet);
    }

    @Override
    public void likeTweet(Long tweetId, CredentialsDto credentialsDto) {
        // Find the tweet
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }
        Tweet tweet = optionalTweet.get();

        if (tweet.isDeleted()) {
            throw new NotFoundException("Tweet has been deleted");
        }

        // Find the user who is liking
        User user = userRepository.findByCredentialsUsername(credentialsDto.getUsername());
        if (user == null || user.isDeleted()) {
            throw new NotFoundException("User doesn't exist or has been deleted");
        }

        // Check credentials
        Credentials requestCreds = credentialsMapper.dtoToEntity(credentialsDto);
        if (!user.getCredentials().equals(requestCreds)) {
            throw new NotAuthorizedException("Credentials don't match");
        }

        // Avoid duplicate likes
        if (!tweet.getLikedBy().contains(user)) {
            tweet.getLikedBy().add(user);
            user.getLikedTweets().add(tweet);

            userRepository.save(user);
            tweetRepository.save(tweet);
        }
    }

    // Helper method for mentions
    private void processMentions(Tweet tweet) {
        String content = tweet.getContent();
        String[] words = content.split("\\s+");

        for (String word : words) {
            if (word.startsWith("@")) {
                String username = word.substring(1);
                User user = userRepository.findByCredentialsUsername(username);

                if (user != null && !user.isDeleted()) {
                    user.getMentionedIn().add(tweet);
                    tweet.getMentions().add(user);
                    userRepository.save(user);
                }
            }
        }
    }

    // Helper method for hashtags
    private void processHashtags(Tweet tweet) {
        String content = tweet.getContent();
        String[] words = content.split("\\s+");

        for (String word : words) {
            if (word.startsWith("#")) {
                String label = word.substring(1);

                Hashtag hashtag = hashtagRepository.findByLabelIgnoreCase(label);
                if (hashtag == null) {
                    hashtag = new Hashtag();
                    hashtag.setLabel(label);
                }

                hashtag.getTweets().add(tweet);
                tweet.getHashtags().add(hashtag);
                hashtagRepository.save(hashtag);
            }
        }
    }

    // Helper method for mentions and hashtags
    private void processMentionsAndHashtags(Tweet tweet) {
        processMentions(tweet);
        processHashtags(tweet);
    }

}

