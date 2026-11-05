package com.cooksys.ftd.social_media.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp // automatically set on insert
    private Timestamp posted;
    private boolean deleted = false; // default to false upon creation
    private String content;

    // Self-referencing
    @ManyToOne
    @JoinColumn(name = "inReplyTo")
    private Tweet inReplyTo;

    // All tweets that are replies to this tweet
    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> replies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "repostOf")
    private Tweet repostOf;

    // All tweets that are reposts of this tweet
    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> reposts = new ArrayList<>();

    // Author
    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;

    // Mentions
    @ManyToMany(mappedBy = "mentionedIn")
    private Set<User> mentions = new HashSet<>();

    // Likes
    @ManyToMany(mappedBy = "likedTweets")
    private Set<User> likedBy = new HashSet<>();

    // Hashtags
    @ManyToMany
    @JoinTable(
        name = "tweet_hashtags",
        joinColumns = @JoinColumn(name = "tweet_id"),
        inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<Hashtag> hashtags = new HashSet<>();
}

