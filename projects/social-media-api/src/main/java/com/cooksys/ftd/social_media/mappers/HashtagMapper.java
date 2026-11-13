package com.cooksys.ftd.social_media.mappers;

import com.cooksys.ftd.social_media.dtos.HashtagDto;
import com.cooksys.ftd.social_media.entities.Hashtag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {
    HashtagDto entityToDto(Hashtag hashtag);

    List<HashtagDto> entitiesToDtos(List<Hashtag> hashtags);
}
