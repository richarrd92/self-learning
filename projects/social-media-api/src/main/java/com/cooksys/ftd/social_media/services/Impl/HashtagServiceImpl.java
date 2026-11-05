package com.cooksys.ftd.social_media.services.Impl;
import com.cooksys.ftd.social_media.dtos.HashtagDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.entities.Hashtag;
import com.cooksys.ftd.social_media.entities.Tweet;
import com.cooksys.ftd.social_media.exceptions.NotFoundException;
import com.cooksys.ftd.social_media.mappers.HashtagMapper;
import com.cooksys.ftd.social_media.mappers.TweetMapper;
import com.cooksys.ftd.social_media.repositories.HashtagRepository;
import com.cooksys.ftd.social_media.repositories.TweetRepository;
import org.springframework.stereotype.Service;
import com.cooksys.ftd.social_media.services.HashtagService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public List<HashtagDto> getAllHashtags() {
        return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
    }

    @Override
    public List<TweetResponseDto> getTweetsByHashtag(String label) {
        Hashtag hashtag = hashtagRepository.findByLabelIgnoreCase(label);

        if (hashtag == null) {
            throw new NotFoundException("No hashtag found with label: " + label);
        }

        List<Tweet> tweets = tweetRepository.findAllByHashtagsAndDeletedFalseOrderByPostedDesc(hashtag);

        return tweetMapper.entitiesToDtos(tweets);

    }
}
