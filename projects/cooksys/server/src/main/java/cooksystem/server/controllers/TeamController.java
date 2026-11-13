package cooksystem.server.controllers;

import cooksystem.server.dtos.TeamDto;
import cooksystem.server.dtos.TeamRequestDto;
import cooksystem.server.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/{companyId}")
    public List<TeamDto> getTeams(@PathVariable Long companyId) throws Exception {
        return teamService.getAllTeams(companyId);
    }

    @PostMapping("/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDto createTeam(@PathVariable Long companyId, @RequestBody TeamRequestDto teamRequestDto) throws Exception {
        return teamService.createTeam(companyId, teamRequestDto);
    }
}
