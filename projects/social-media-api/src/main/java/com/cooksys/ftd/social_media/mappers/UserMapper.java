package com.cooksys.ftd.social_media.mappers;

import com.cooksys.ftd.social_media.dtos.UserRequestDto;
import com.cooksys.ftd.social_media.dtos.UserResponseDto;
import com.cooksys.ftd.social_media.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class})
public interface UserMapper {

    // Map embedded fields from User entity to UserResponseDto:
    @Mapping(source = "credentials.username", target = "username")
    @Mapping(source = "profile", target = "profile")
    UserResponseDto entityToDto(User entity);

    List<UserResponseDto> entitiesToDtos(List<User> entities);

    User dtoToEntity(UserRequestDto dto);

    List<User> dtosToEntities(List<UserRequestDto> dtos);

}