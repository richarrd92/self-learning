package com.cooksys.ftd.social_media.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;

    private String label;

    @CreationTimestamp // automatically set on insert
    private Timestamp firstUsed;

    @CreationTimestamp
    private Timestamp lastUsed;

    @ManyToMany(mappedBy = "hashtags")
    private Set<Tweet> tweets = new HashSet<>();
}
