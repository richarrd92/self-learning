package com.cooksys.ftd.social_media.mappers;

import com.cooksys.ftd.social_media.dtos.CredentialsDto;
import com.cooksys.ftd.social_media.entities.embeddables.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    CredentialsDto entityToDto(Credentials credentials);

    Credentials dtoToEntity(CredentialsDto credentialsDto);
}
