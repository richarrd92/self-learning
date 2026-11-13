package cooksystem.server.mappers;

import cooksystem.server.dtos.ProfileDto;
import cooksystem.server.entities.embaddables.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto entityToDto(Profile profile);
    Profile dtoToEntity(ProfileDto profileDto);
}
