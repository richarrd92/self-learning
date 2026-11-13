package cooksystem.server.controllers;

import cooksystem.server.dtos.CredentialsRequestDto;
import cooksystem.server.dtos.CredentialsResponseDto;
import cooksystem.server.dtos.LogoutResponseDto;
import cooksystem.server.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * POST /auth/login
     * Authenticates a user using username or email + password.
     * Creates a new session storing user ID & admin status.
     */
    @PostMapping("/login")
    public CredentialsResponseDto login(
            @RequestBody CredentialsRequestDto credentialsRequestDto,
            HttpServletRequest request) {
        return authService.loginWithSession(credentialsRequestDto, request);
    }

    /**
     * POST /auth/logout
     * Delegates logout to AuthService, which invalidates the session.
     */
    @PostMapping("/logout")
    public LogoutResponseDto logout(HttpServletRequest request) {
        return authService.logout(request);
    }
}
