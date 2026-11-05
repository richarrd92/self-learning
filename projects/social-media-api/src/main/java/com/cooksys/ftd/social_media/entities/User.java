package com.cooksys.ftd.social_media.entities;
import com.cooksys.ftd.social_media.entities.embeddables.Credentials;
import com.cooksys.ftd.social_media.entities.embeddables.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp // automatically set on insert
    private Timestamp joined;

    private boolean deleted = false; // default to false upon user creation

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    // many-to-many
    @ManyToMany
    @JoinTable(
        name = "followers_following",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    // Tweets authored by this user
    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets = new ArrayList<>();

    // Tweets liked
    @ManyToMany
    @JoinTable(
        name = "user_likes",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private Set<Tweet> likedTweets = new HashSet<>();

    // Mentions
    @ManyToMany
    @JoinTable(
        name = "user_mentions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "tweet_id")
    )
    private Set<Tweet> mentionedIn = new HashSet<>();

}
