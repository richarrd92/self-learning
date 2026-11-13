package cooksystem.server.controllers;

import cooksystem.server.dtos.*;
import cooksystem.server.exceptions.AuthenticationException;
import cooksystem.server.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * GET /users/{id}
     * Returns user by id (admin only).
     * Controller validates that user is logged in via session.
     * Service handles admin check and business logic.
     */
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id, HttpSession httpSession){
        Long userId = getUserIdFromSession(httpSession);
        return userService.getUser(id, userId);
    }

    /**
     * GET /users
     * Returns all users (admin only).
     * Controller validates that user is logged in via session.
     * Service handles admin check and business logic.
     */
    @GetMapping
    public List<UserResponseDto> getAllUsers(HttpSession httpSession){
        Long userId = getUserIdFromSession(httpSession);
        return userService.getAllUsers(userId);
    }

    /**
     * GET /users/company/{companyId}
     * Returns all users in a company (admin only).
     * Controller validates that user is logged in via session.
     * Service handles admin check and business logic.
     */
    @GetMapping("/company/{companyId}")
    public List<UserResponseDto> getUsersByCompany(@PathVariable Long companyId, HttpSession httpSession){
        Long userId = getUserIdFromSession(httpSession);
        return userService.getUsersByCompany(companyId, userId);
    }

    /**
     * POST /users
     * Creates user (admin only).
     * Returns user dto from database
     * Controller validates that user is logged in via session.
     * Service handles admin check and business logic.
     */
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto,
                                     @RequestParam Long companyId,
                                     HttpSession httpSession){
        Long userId = getUserIdFromSession(httpSession);
        return userService.createUser(userRequestDto, companyId, userId);
    }

    /**
     * Helper method: retrieves userId from session and validates login.
     */
    private Long getUserIdFromSession(HttpSession httpSession) {
        // Validate session: check if user is logged in
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId == null) {
            throw new AuthenticationException(
                    "Not logged in",
                    "UNAUTHORIZED",
                    "You must log in first"
            );
        }
        return userId;
    }
}
