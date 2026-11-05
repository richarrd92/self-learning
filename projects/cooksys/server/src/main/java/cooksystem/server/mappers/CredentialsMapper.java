package cooksystem.server.mappers;

import cooksystem.server.dtos.CredentialsRequestDto;
import cooksystem.server.dtos.CredentialsResponseDto;
import cooksystem.server.entities.embaddables.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    CredentialsResponseDto entityToDto(Credentials credentials);
    Credentials dtoToEntity(CredentialsRequestDto credentialsRequestDto);
}
