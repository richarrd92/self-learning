package com.cooksys.ftd.social_media.mappers;

import com.cooksys.ftd.social_media.dtos.ProfileDto;
import com.cooksys.ftd.social_media.entities.embeddables.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto entityToDto(Profile profile);

    Profile dtoToEntity(ProfileDto profileDto);
}

