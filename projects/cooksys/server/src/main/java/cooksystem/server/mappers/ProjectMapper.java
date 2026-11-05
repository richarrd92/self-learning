package cooksystem.server.mappers;

import cooksystem.server.dtos.ProjectDto;
import cooksystem.server.dtos.ProjectRequestDto;
import cooksystem.server.entities.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface ProjectMapper {
    ProjectDto entityToDto(Project project);
    List<ProjectDto> entitiesToDtos(List<Project> projects);
    Project requestDtoToEntity(ProjectRequestDto projectRequestDto);
}
