package hobbymatch.backend.mappers;

import hobbymatch.backend.dtos.auth.AuthResponseDto;
import hobbymatch.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface to convert User entities to authentication-related DTOs.
 * Uses MapStruct to automatically generate mapping implementations at compile time.
 */
@Mapper(componentModel = "spring")
public interface AuthMapper {

    // JWT token generated programmatically
    @Mapping(target = "token", ignore = true)
    AuthResponseDto toAuthDto(User user);
}
