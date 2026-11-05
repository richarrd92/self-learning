package com.cooksys.ftd.social_media.repositories;

import com.cooksys.ftd.social_media.entities.Hashtag;
import com.cooksys.ftd.social_media.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByDeletedFalse();

    List<Tweet> findAllByHashtagsAndDeletedFalseOrderByPostedDesc(Hashtag hashtag);

    Optional<Tweet> findByIdAndDeletedFalse(Long tweetId);
}
