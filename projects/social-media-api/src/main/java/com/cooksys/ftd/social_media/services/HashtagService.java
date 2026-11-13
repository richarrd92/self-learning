package com.cooksys.ftd.social_media.services;

import com.cooksys.ftd.social_media.dtos.HashtagDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.entities.Hashtag;

import java.util.List;

public interface HashtagService {
    List<HashtagDto> getAllHashtags();
    List<TweetResponseDto> getTweetsByHashtag(String label);
}
