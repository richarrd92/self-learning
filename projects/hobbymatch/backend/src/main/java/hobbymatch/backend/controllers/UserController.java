package hobbymatch.backend.controllers;

import hobbymatch.backend.dtos.user.*;
import hobbymatch.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling user-related API endpoints.
 * Exposes routes under "/api/users" to perform CRUD operations on user information.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    /**
     * GET endpoint to retrieve all users.
     * @return list of UserResponseDto containing user details
     */
    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     * GET endpoint to retrieve the currently authenticated user.
     * @param authHeader the "Authorization" header containing the JWT token
     * @return UserResponseDto for the logged-in user
     */
    @GetMapping("/me")
    public UserResponseDto getCurrentUser(@RequestHeader("Authorization") String authHeader){
        return userService.getCurrentUser(authHeader);
    }

    /**
     * GET endpoint to retrieve a user by their ID.
     * @param userId the user's ID
     * @return UserResponseDto for the specified user
     */
    @GetMapping("/id/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    /**
     * GET endpoint to retrieve a user by their username.
     * @param username the user's username
     * @return UserResponseDto for the specified user
     */
    @GetMapping("/username/{username}")
    public UserResponseDto getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    /**
     * PATCH endpoint to update a user's username.
     * @param userId the user's ID
     * @param updateUsernameRequestDto DTO containing the new username
     * @return updated UserResponseDto
     */
    @PatchMapping("/{userId}/username")
    public UserResponseDto updateUsername(@PathVariable Long userId,
                                          @Valid @RequestBody UpdateUsernameRequestDto updateUsernameRequestDto) {
        return userService.updateUsername(userId, updateUsernameRequestDto.getUsername());
    }

    /**
     * PATCH endpoint to update a user's bio.
     * @param userId the user's ID
     * @param updateBioRequestDto DTO containing the new bio
     * @return updated UserResponseDto
     */
    @PatchMapping("/{userId}/bio")
    public UserResponseDto updateBio(@PathVariable Long userId,
                                     @Valid @RequestBody UpdateBioRequestDto updateBioRequestDto) {
        return userService.updateBio(userId, updateBioRequestDto.getBio());
    }

    /**
     * PATCH endpoint to update a user's profile privacy (public/private).
     * @param userId the user's ID
     * @param updatePrivacyRequestDto DTO containing the new privacy setting
     * @return updated UserResponseDto
     */
    @PatchMapping("/{userId}/privacy")
    public UserResponseDto updatePrivacy(
            @PathVariable Long userId,
            @RequestBody UpdatePrivacyRequestDto updatePrivacyRequestDto) {
        return userService.updatePrivacy(userId, updatePrivacyRequestDto.isPublic());
    }

    /**
     * PATCH endpoint to update a user's location.
     * @param userId the user's ID
     * @param updateLocationRequestDto DTO containing the new location
     * @return updated UserResponseDto
     */
    @PatchMapping("/{userId}/location")
    public UserResponseDto updateLocation(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateLocationRequestDto updateLocationRequestDto) {
        return userService.updateLocation(userId, updateLocationRequestDto.getLocation());
    }

    /**
     * PATCH endpoint to update a user's hobbies.
     * @param userId the user's ID
     * @param updateHobbiesRequestDto DTO containing a list of hobby IDs
     * @return updated UserResponseDto
     */
    @PatchMapping("/{userId}/hobbies")
    public UserResponseDto updateHobbies(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateHobbiesRequestDto updateHobbiesRequestDto) {
        return userService.updateHobbies(userId, updateHobbiesRequestDto.getHobbies());
    }
}
