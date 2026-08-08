package hobbymatch.backend.controllers;

import hobbymatch.backend.dtos.hobby.HobbyResponseDto;
import hobbymatch.backend.dtos.user.UpdateHobbiesRequestDto;
import hobbymatch.backend.services.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling hobby-related API endpoints.
 * Exposes routes under "/api/hobbies" to perform CRUD operations on hobby information.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hobbies")
public class HobbyController {
    private final HobbyService hobbyService;

    /**
     * GET endpoint to retrieve all hobbies.
     * @return list of HobbyResponseDto containing hobby details
     */
    @GetMapping
    public List<HobbyResponseDto> getAllHobbies(){
        return hobbyService.getAllHobbies();
    }

    /**
     * GET endpoint to retrieve all user hobbies.
     * @param userId the user's ID
     * @return list of HobbyResponseDto containing user hobby details
     */
    @GetMapping("/{userId}")
    public List<HobbyResponseDto> getUserHobbies(@PathVariable Long userId){
        return hobbyService.getUserHobbies(userId);
    }

    /**
     * PUT endpoint to update a user's hobbies.
     * @param userId the user's ID
     * @param updateHobbiesRequestDto DTO containing the new hobby ids
     * @return updated List<HobbyResponseDto>
     */
    @PutMapping("/{userId}/hobbies")
    public List<HobbyResponseDto> updateUserHobbies(
            @PathVariable Long userId,
            @RequestBody UpdateHobbiesRequestDto updateHobbiesRequestDto
            ) {
        return hobbyService.updateUserHobbies(userId, updateHobbiesRequestDto.getHobbies());
    }
}
