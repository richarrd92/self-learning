package cooksystem.server.mappers;

import cooksystem.server.dtos.UserStateDto;
import cooksystem.server.entities.embaddables.UserState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStateMapper {
    UserStateDto entityToDto(UserState userState);
    UserState dtoToEntity(UserStateDto userStateDto);
}
