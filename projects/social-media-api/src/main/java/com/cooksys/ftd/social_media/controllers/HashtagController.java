package com.cooksys.ftd.social_media.controllers;
import com.cooksys.ftd.social_media.dtos.HashtagDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.services.HashtagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {
    private final HashtagService hashtagService;

    @GetMapping
    public List<HashtagDto> getAllHashtags(){
        return hashtagService.getAllHashtags();
    }

    @GetMapping("/{label}")
    public List<TweetResponseDto> getTweetsByHashTag(@PathVariable String label){
        return hashtagService.getTweetsByHashtag(label);
    }
}
