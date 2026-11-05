package cooksystem.server.mappers;

import cooksystem.server.dtos.TeamDto;
import cooksystem.server.dtos.TeamRequestDto;
import cooksystem.server.entities.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface TeamMapper {

    TeamDto entityToDto(Team team);
    List<TeamDto> entitiesToDtos(List<Team> teams);
    Team requestDtoToEntity(TeamRequestDto teamRequestDto);

}
