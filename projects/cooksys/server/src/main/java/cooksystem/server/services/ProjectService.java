package cooksystem.server.services;

import cooksystem.server.dtos.ProjectDto;
import cooksystem.server.dtos.ProjectRequestDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects(Long companyId, Long teamId) throws Exception;

    ProjectDto createProject(Long companyId, Long teamId, ProjectRequestDto projectRequestDto) throws Exception;

    ProjectDto updateProject(Long companyId, Long teamId, Long projectId, ProjectRequestDto projectRequestDto) throws Exception;
}
