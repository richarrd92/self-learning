package cooksystem.server.services.Implementations;

import cooksystem.server.dtos.ProjectDto;
import cooksystem.server.dtos.ProjectRequestDto;
import cooksystem.server.entities.Company;
import cooksystem.server.entities.Project;
import cooksystem.server.entities.Team;
import cooksystem.server.exceptions.BadRequestException;
import cooksystem.server.exceptions.NotFoundException;
import cooksystem.server.mappers.ProjectMapper;
import cooksystem.server.repositories.ProjectRepository;
import cooksystem.server.services.CompanyService;
import cooksystem.server.services.ProjectService;
import cooksystem.server.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

    private final CompanyService companyService;
    private final TeamService teamService;

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getAllProjects(Long companyId, Long teamId) throws Exception {
        Company company = companyService.findCompany(companyId);
        Team team = teamService.findTeam(teamId);

        if (!company.getTeams().contains(team)) {
            throw new NotFoundException("This team does not exist at company " + companyId);
        }

        return projectMapper.entitiesToDtos(team.getProjects());
    }

    @Override
    public ProjectDto createProject(Long companyId, Long teamId, ProjectRequestDto projectRequestDto) throws Exception {
        Company company = companyService.findCompany(companyId);
        Team team = teamService.findTeam(teamId);

        if (!team.getCompany().equals(company)) {
            throw new NotFoundException("This team does not exist at company " + companyId);
        }
        if (projectRequestDto.getName() == null || projectRequestDto.getName().isBlank()) {
            throw new BadRequestException("Project name is required");
        }

        Project project = projectMapper.requestDtoToEntity(projectRequestDto);
        project.setTeam(team);
        return projectMapper.entityToDto(projectRepository.saveAndFlush(project));
    }

    @Override
    public ProjectDto updateProject(Long companyId, Long teamId, Long projectId, ProjectRequestDto projectRequestDto) throws Exception {
        Company company = companyService.findCompany(companyId);
        Team team = teamService.findTeam(teamId);
        Project project = findProject(projectId);
        if (!team.getCompany().equals(company)) {
            throw new BadRequestException("This team does not exist at company " + companyId);
        }
        if (!project.getTeam().equals(team)) {
            throw new BadRequestException("Team " + teamId + " does not have project " + projectId);
        }
        if (projectRequestDto.getName() == null || projectRequestDto.getName().isBlank()) {
            throw new BadRequestException("Project name is required");
        }
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setActive(projectRequestDto.isActive());
        project.setTeam(team);
        return projectMapper.entityToDto(projectRepository.saveAndFlush(project));
    }

    public Project findProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("A project with the provided id does not exist.");
        }
        return project.get();
    }
}
