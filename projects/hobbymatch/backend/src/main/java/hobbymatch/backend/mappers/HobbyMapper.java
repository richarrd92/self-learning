package hobbymatch.backend.mappers;

import hobbymatch.backend.dtos.hobby.HobbyResponseDto;
import hobbymatch.backend.entities.Hobby;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface to convert Hobby entities to DTOs for responses.
 * Uses MapStruct to automatically generate mapping implementations at compile time.
 */
@Mapper(componentModel = "spring")
public interface HobbyMapper {
    HobbyResponseDto toResponse(Hobby hobby);
    List<HobbyResponseDto> toResponseList(List<Hobby> hobbies);
}
