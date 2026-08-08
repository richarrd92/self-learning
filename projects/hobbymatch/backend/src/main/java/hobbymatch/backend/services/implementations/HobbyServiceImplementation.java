package hobbymatch.backend.services.implementations;

import hobbymatch.backend.dtos.hobby.HobbyResponseDto;
import hobbymatch.backend.entities.Hobby;
import hobbymatch.backend.entities.User;
import hobbymatch.backend.exceptions.UserException;
import hobbymatch.backend.mappers.HobbyMapper;
import hobbymatch.backend.repositories.HobbyRepository;
import hobbymatch.backend.repositories.UserRepository;
import hobbymatch.backend.services.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for hobby-related operations.
 * Handles fetching hobby data and mapping it to DTOs for the API.
 */
@Service
@RequiredArgsConstructor
public class HobbyServiceImplementation implements HobbyService {
    private final HobbyRepository hobbyRepository;
    private final UserRepository userRepository;
    private final HobbyMapper hobbyMapper;

    /**
     * Retrieves all hobbies from the database and converts them to response DTOs.
     * @return a list of HobbyResponseDto containing hobby information
     */
    @Override
    public List<HobbyResponseDto> getAllHobbies() {
        return hobbyMapper.toResponseList(hobbyRepository.findAll());
    }

    /**
     * Retrieves all hobbies from the database and converts them to response DTOs.
     * @return a list of HobbyResponseDto containing user hobby information
     */
    @Override
    public List<HobbyResponseDto> getUserHobbies(Long userId) {
        User user = getUserOrThrow(userId);
        return hobbyMapper.toResponseList(user.getHobbies().stream().toList());
    }

    @Override
    public List<HobbyResponseDto> updateUserHobbies(Long userId, List<Long> hobbyIds) {
        User user = getUserOrThrow(userId);

        // Fetch Hobby entities for the given IDs
        List<Hobby> hobbies = hobbyRepository.findAllById(hobbyIds);

        // Replace user's hobbies with the new selection
        user.setHobbies(hobbies);

        // Save updated user
        userRepository.save(user);

        // Return updated hobbies as DTOs
        return hobbyMapper.toResponseList(user.getHobbies());
    }


    /**
     * Helper method: fetch user by ID or throw exception if not found.
     * @param userId user ID
     * @return User entity
     * @throws UserException if user not found
     */
    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserException.notFound("User not found"));
    }
}
