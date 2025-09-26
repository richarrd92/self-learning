//package com.cooksys.ftd.social_media;
//
//import com.cooksys.ftd.social_media.entities.Hashtag;
//import com.cooksys.ftd.social_media.entities.Tweet;
//import com.cooksys.ftd.social_media.entities.User;
//import com.cooksys.ftd.social_media.entities.embeddables.Credentials;
//import com.cooksys.ftd.social_media.entities.embeddables.Profile;
//import com.cooksys.ftd.social_media.repositories.HashtagRepository;
//import com.cooksys.ftd.social_media.repositories.TweetRepository;
//import com.cooksys.ftd.social_media.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class Seeder implements CommandLineRunner {
//
//    // Repositories
//    private final UserRepository userRepository;
//    private final TweetRepository tweetRepository;
//    private final HashtagRepository hashtagRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // ---- Users ----
//        // Create Richard
//        User richard = new User();
//        Credentials richardCredentials = new Credentials(); // Credentials
//        richardCredentials.setUsername("richard");
//        richardCredentials.setPassword("pass1");
//        richard.setCredentials(richardCredentials);
//
//        Profile richardProfile = new Profile(); // Profile
//        richardProfile.setFirstName("Richard");
//        richardProfile.setLastName("N/a");
//        richardProfile.setEmail("richard@example.com");
//        richardProfile.setPhone("9999999999");
//        richard.setProfile(richardProfile);
//
//        // Create Akib
//        User akib = new User();
//        Credentials akibCredentials = new Credentials(); // Credentials
//        akibCredentials.setUsername("akib");
//        akibCredentials.setPassword("pass2");
//        akib.setCredentials(akibCredentials);
//
//        Profile akibProfile = new Profile(); // Profile
//        akibProfile.setFirstName("Akib");
//        akibProfile.setLastName("N/a");
//        akibProfile.setEmail("akib@example.com");
//        akibProfile.setPhone("8888888888");
//        akib.setProfile(akibProfile);
//
//        // Create Randy
//        User randy = new User();
//        Credentials randyCredentials = new Credentials(); // Credentials
//        randyCredentials.setUsername("randy");
//        randyCredentials.setPassword("pass3");
//        randy.setCredentials(randyCredentials);
//
//        Profile randyProfile = new Profile(); // Profile
//        randyProfile.setFirstName("Randy");
//        randyProfile.setLastName("N/a");
//        randyProfile.setEmail("randy@example.com");
//        randyProfile.setPhone("7777777777");
//        randy.setProfile(randyProfile);
//
//        // ---- Followers/Following ----
//        // Richard follows Akib
//        // Akib follows Randy
//        // Randy follows Richard
//        richard.setFollowing(new HashSet<>(List.of(akib)));
//        akib.setFollowers(new HashSet<>(List.of(richard)));
//
//        akib.setFollowing(new HashSet<>(List.of(randy)));
//        randy.setFollowers(new HashSet<>(List.of(akib)));
//
//        randy.setFollowing(new HashSet<>(List.of(richard)));
//        richard.setFollowers(new HashSet<>(List.of(randy)));
//
//        // Save all users - in database
//        userRepository.saveAll(List.of(richard, akib, randy));
//
//        // ---- Tweets ----
//        // Richard posts a tweet
//        Tweet tweet1 = new Tweet();
//        tweet1.setAuthor(richard);
//        tweet1.setContent("Excited to work on this project! #java @akib");
//
//        // Akib replies to Richard tweet
//        Tweet tweet2 = new Tweet();
//        tweet2.setAuthor(akib);
//        tweet2.setContent("Replying to Richard — let's build this! #spring");
//        tweet2.setInReplyTo(tweet1);
//
//        // Randy reposts Richard's tweet
//        Tweet tweet3 = new Tweet();
//        tweet3.setAuthor(randy);
//        tweet3.setRepostOf(tweet1);
//
//        // Save tweets
//        tweetRepository.saveAll(List.of(tweet1, tweet2, tweet3));
//
//        // ---- Hashtags ----
//        // Create #java hashtag and associate with Richard’s tweet
//        Hashtag java = new Hashtag();
//        java.setLabel("java");
//        java.setTweets(new HashSet<>(List.of(tweet1)));
//
//        // Create #spring hashtag and associate with Akib’s reply
//        Hashtag spring = new Hashtag();
//        spring.setLabel("spring");
//        spring.setTweets(new HashSet<>(List.of(tweet2)));
//
//        // Save hashtags
//        hashtagRepository.saveAll(List.of(java, spring));
//
//        // Link hashtags back to tweets
//        tweet1.setHashtags(new HashSet<>(List.of(java)));
//        tweet2.setHashtags(new HashSet<>(List.of(spring)));
//        tweetRepository.saveAll(List.of(tweet1, tweet2));
//
//        // ---- Likes ----
//        // Users like tweets using helper methods (bidirectional update)
//        akib.likeTweet(tweet1); // Akib likes Richard's tweet
//        randy.likeTweet(tweet1); // Randy likes Richard's tweet
//        richard.likeTweet(tweet2); // Richard likes Akib's reply
//
//        tweetRepository.saveAll(List.of(tweet1, tweet2)); // Save updated likes
//
//        // ---- Mentions ----
//        // Register mentions using helper methods (bidirectional)
//        akib.isMentionedIn(tweet1); // Richard mentions Akib in tweet1
//
//        // Persist updated mentions and users
//        tweetRepository.save(tweet1);
//        userRepository.saveAll(List.of(richard, akib, randy));
//    }
//}
