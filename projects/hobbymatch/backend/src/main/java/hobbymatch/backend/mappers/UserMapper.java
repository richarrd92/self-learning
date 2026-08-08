package hobbymatch.backend.mappers;

import hobbymatch.backend.dtos.auth.AuthResponseDto;
import hobbymatch.backend.dtos.user.UserResponseDto;
import hobbymatch.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface to convert User entities to DTOs for authentication and user responses.
 * Uses MapStruct to automatically generate mapping implementations at compile time.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    // JWT token generated programmatically
    @Mapping(target = "token", ignore = true)
    AuthResponseDto toRegisterResponse(User user);
    UserResponseDto toUserResponse(User user);
    List<UserResponseDto> toUserResponseList(List<User> users);
}

