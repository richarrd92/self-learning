package cooksystem.server.services;

import cooksystem.server.dtos.TeamDto;
import cooksystem.server.dtos.TeamRequestDto;
import cooksystem.server.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<TeamDto> getAllTeams(Long id) throws Exception;

    TeamDto createTeam(Long id, TeamRequestDto teamRequestDto) throws Exception;

    Team findTeam(Long id);
}
