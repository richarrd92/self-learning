package com.cooksys.ftd.social_media.mappers;

import com.cooksys.ftd.social_media.dtos.TweetRequestDto;
import com.cooksys.ftd.social_media.dtos.TweetResponseDto;
import com.cooksys.ftd.social_media.entities.Tweet;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

    TweetResponseDto entityToDto(Tweet entity);

    List<TweetResponseDto> entitiesToDtos(List<Tweet> entities);

    Tweet dtoToEntity(TweetRequestDto dto);

    List<Tweet> dtosToEntities(List<TweetRequestDto> dtos);
}