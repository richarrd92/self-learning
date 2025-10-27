package cooksystem.server.controllers;

import cooksystem.server.dtos.ProjectDto;
import cooksystem.server.dtos.ProjectRequestDto;
import cooksystem.server.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{companyId}/{teamId}")
    public List<ProjectDto> getProjects(@PathVariable Long companyId, @PathVariable Long teamId) throws Exception {
        return projectService.getAllProjects(companyId, teamId);
    }

    @PostMapping("/{companyId}/{teamId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@PathVariable Long companyId, @PathVariable Long teamId, @RequestBody ProjectRequestDto projectRequestDto) throws Exception {
        return projectService.createProject(companyId, teamId, projectRequestDto);
    }

    @PatchMapping("/{companyId}/{teamId}/{projectId}")
    public ProjectDto updateProject(@PathVariable Long companyId, @PathVariable Long teamId, @PathVariable Long projectId, @RequestBody ProjectRequestDto projectRequestDto) throws Exception {
        return projectService.updateProject(companyId, teamId, projectId, projectRequestDto);
    }

}
