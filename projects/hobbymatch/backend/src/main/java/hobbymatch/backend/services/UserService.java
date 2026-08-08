package hobbymatch.backend.services;

import hobbymatch.backend.dtos.user.UserResponseDto;

import java.util.List;

/**
 * Service interface for user-related operations.
 * Defines methods for retrieving and updating user information.
 */
public interface UserService {
    UserResponseDto getCurrentUser(String token);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long userId);
    UserResponseDto getUserByUsername(String username);
    UserResponseDto updateUsername(Long userId, String newUsername);
    UserResponseDto updateBio(Long userId, String bio);
    UserResponseDto updatePrivacy(Long userId, boolean isPublic);
    UserResponseDto updateLocation(Long userId, String location);
    UserResponseDto updateHobbies(Long userId, List<Long> hobbyIds);
}
