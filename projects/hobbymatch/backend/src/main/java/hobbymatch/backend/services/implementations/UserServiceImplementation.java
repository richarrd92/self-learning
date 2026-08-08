package hobbymatch.backend.services.implementations;

import hobbymatch.backend.dtos.user.UserResponseDto;
import hobbymatch.backend.entities.Hobby;
import hobbymatch.backend.entities.User;
import hobbymatch.backend.entities.embaddables.EmbeddedLocation;
import hobbymatch.backend.exceptions.JwtException;
import hobbymatch.backend.exceptions.UserException;
import hobbymatch.backend.mappers.UserMapper;
import hobbymatch.backend.repositories.HobbyRepository;
import hobbymatch.backend.repositories.UserRepository;
import hobbymatch.backend.services.JwtService;
import hobbymatch.backend.services.LocationService;
import hobbymatch.backend.services.UserService;
import hobbymatch.backend.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Service implementation for handling all user-related operations.
 * Includes fetching users, updating profiles, handling hobbies, locations,
 * and parsing JWT tokens to get the current user.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;
    private final ValidationService validationService;
    private final LocationService locationService;
    private final JwtService jwtService;

    /**
     * Fetch all users from the database.
     * @return a list of all users as list of UserResponseDtos
     */
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    /**
     * Fetch a single user by their ID.
     * @param userId ID of the user
     * @return UserResponseDto containing user info
     */
    @Override
    public UserResponseDto getUserById(Long userId) {
        return userMapper.toUserResponse(getUserOrThrow(userId));
    }

    /**
     * Fetch a user by username.
     * @param username unique username
     * @return UserResponseDto for the found user
     * @throws UserException if the user is not found
     */
    @Override
    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserException.notFound("User not found"));
        return userMapper.toUserResponse(user);
    }

    /**
     * Fetch the currently authenticated user from a JWT token.
     * @param token Bearer JWT token
     * @return UserResponseDto for the authenticated user
     * @throws JwtException if token is missing, invalid, or expired
     * @throws UserException if the user is inactive or not found
     */
    @Override
    public UserResponseDto getCurrentUser(String token) {
        if (token == null || token.isBlank()) {
            throw JwtException.missingToken();
        }

        // Remove "Bearer " prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // Extract user ID from JWT token
            Long userId = jwtService.getUserIdFromToken(token);

            // Fetch the corresponding user
            UserResponseDto user = getUserById(userId);

            // Check if user account is active before allowing access
            if (!user.isActive()) {
                throw UserException.userInactive();
            }

            // Return valid, active user
            return user;

        } catch (Exception e) {
            // Handle any token parsing, expiration, validation or signature error
            // Same semantic meaning
            throw JwtException.invalidOrExpired();
        }
    }

    /**
     * Update the username for a user.
     * @param userId ID of the user
     * @param newUsername new desired username
     * @return updated UserResponseDto
     * @throws UserException if username is invalid or already taken
     */
    @Override
    public UserResponseDto updateUsername(Long userId, String newUsername) {
        // TODO: enforce cooldown on username change (e.g., every 2 weeks)
        User user = getUserOrThrow(userId);
        if (validationService.isUsernameValid(newUsername)) {
            throw UserException.duplicateUsername("Username already taken or invalid");
        }

        user.setUsername(newUsername);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    /**
     * Update user's bio/description.
     * @param userId ID of the user
     * @param bio new biography text
     * @return updated UserResponseDto
     */
    @Override
    public UserResponseDto updateBio(Long userId, String bio) {
        User user = getUserOrThrow(userId);
        user.setBio(bio);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    /**
     * Update a user's privacy setting.
     * @param userId ID of the user
     * @param isPublic true if profile is public, false if private
     * @return updated UserResponseDto
     */
    @Override
    public UserResponseDto updatePrivacy(Long userId, boolean isPublic) {
        User user = getUserOrThrow(userId);
        user.setPublic(isPublic);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    /**
     * Update a user's location using a location name (city).
     * Calls the LocationService to fetch coordinates via OpenStreetMap (Nominatim).
     * @param userId ID of the user
     * @param location name or input location string
     * @return updated UserResponseDto
     */
    @Override
    public UserResponseDto updateLocation(Long userId, String location) {
        User user = getUserOrThrow(userId);
        EmbeddedLocation embeddedLocation = locationService.getLocation(location);
        user.setEmbeddedLocation(embeddedLocation);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    /**
     * Update a user's hobbies by replacing with a new set of hobby IDs.
     * @param userId ID of the user
     * @param hobbyIds set of hobby IDs
     * @return updated UserResponseDto
     * @throws UserException if any hobby ID is not found
     */
    @Override
    public UserResponseDto updateHobbies(Long userId, List<Long> hobbyIds) {
        User user = getUserOrThrow(userId);

        // Create a new set to hold valid hobby entities
        List<Hobby> hobbies = new ArrayList<>();

        // Validate and retrieve each hobby from the database
        for (Long id : hobbyIds) {
            Hobby hobby = hobbyRepository.findById(id).orElseThrow(
                    () -> UserException.notFound("Hobby not found: " + id)
            );
            hobbies.add(hobby);
        }

        // Update user's hobbies and save the updated entity
        user.setHobbies(hobbies);

        // Map and return updated user response
        return userMapper.toUserResponse(userRepository.save(user));
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
