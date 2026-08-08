package hobbymatch.backend.controllers;

import hobbymatch.backend.dtos.auth.*;
import hobbymatch.backend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for authentication-related endpoints.
 * Exposes API routes for user registration and login.
 * All routes are prefixed with "/api/auth".
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Registers a new user.
     * @param registerRequestDto contains user info (username, password, etc.)
     * @return AuthResponseDto with success info and generated token
     */
    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
        return authService.register(registerRequestDto);
    }

    /**
     * Logs in an existing user.
     * @param loginRequestDto contains login credentials (username, password)
     * @return AuthResponseDto with authentication token and user info
     */
    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}
