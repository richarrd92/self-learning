package hobbymatch.backend.dtos.user;

import hobbymatch.backend.entities.embaddables.EmbeddedLocation;
import hobbymatch.backend.dtos.hobby.HobbyResponseDto;
import hobbymatch.backend.enums.Gender;
import hobbymatch.backend.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for transferring user data from backend to client.
 * Encapsulates all relevant user details including profile info, location, hobbies, and timestamps.
 */
@Data
public class UserResponseDto {
    private Long userId;
    private String name;
    private String username;
    private String bio;
    private Gender gender;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private boolean isPublic;
    private Role role;
    private EmbeddedLocation embeddedLocation;
    private boolean isProfileComplete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<HobbyResponseDto> hobbies;
}
