package hobbymatch.backend.services;

import hobbymatch.backend.dtos.hobby.HobbyResponseDto;

import java.util.List;

/**
 * Service interface for hobby-related operations.
 * Defines the contract for retrieving hobby data.
 */
public interface HobbyService {
    List<HobbyResponseDto> getAllHobbies();
    List<HobbyResponseDto> getUserHobbies(Long userId);
    List<HobbyResponseDto> updateUserHobbies(Long userId, List<Long> hobbyIds);
}
