package cooksystem.server.services.Implementations;

import cooksystem.server.dtos.CredentialsRequestDto;
import cooksystem.server.dtos.CredentialsResponseDto;
import cooksystem.server.dtos.LogoutResponseDto;
import cooksystem.server.entities.User;
import cooksystem.server.entities.embaddables.UserState;
import cooksystem.server.exceptions.AuthenticationException;
import cooksystem.server.repositories.UserRepository;
import cooksystem.server.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final UserRepository userRepository;

    /**
     * Handles user login.
     * Allows authentication via either username or email along with a password.
     */
    @Override
    public CredentialsResponseDto loginWithSession(CredentialsRequestDto credentialsRequestDto, HttpServletRequest request) {
        // Authenticate user - Calls helper function
        User user = authenticateUser(credentialsRequestDto);

        // Update user state on login: set active to true and status to JOINED
        UserState userState = user.getUserState();
        if (userState.getStatus() == UserState.Status.PENDING) {
            userState.setStatus(UserState.Status.JOINED);
        }
        userState.setActive(true);
        userRepository.save(user);

        // Invalidate old session
        HttpSession oldHttpSession = request.getSession(false);
        if (oldHttpSession != null) oldHttpSession.invalidate();

        // Create new session and store user info
        HttpSession newHttpSession = request.getSession(true);
        newHttpSession.setAttribute("userId", user.getId());
        newHttpSession.setAttribute("isAdmin", user.getUserState().isAdmin());
        newHttpSession.setAttribute("username", user.getCredentials().getUsername());

        // Build credentials DTO
        CredentialsResponseDto credentialsResponseDto = new CredentialsResponseDto();
        credentialsResponseDto.setId(user.getId()); // include id
        credentialsResponseDto.setUsername(user.getCredentials().getUsername());
        credentialsResponseDto.setEmail(user.getCredentials().getEmail());
        credentialsResponseDto.setAdmin(user.getUserState().isAdmin());

        // For non-admin users, include their company ID (first company they belong to)
        if (!user.getUserState().isAdmin() && !user.getCompanies().isEmpty()) {
            credentialsResponseDto.setCompanyId(user.getCompanies().iterator().next().getId());
        }

        return credentialsResponseDto;
    }

    /**
     * Authenticates a user using username or email + password.
     */
    private User authenticateUser(CredentialsRequestDto credentialsRequestDto) {
        User user = null;

        // Attempt to find user by username if provided
        if (credentialsRequestDto.getUsername() != null && !credentialsRequestDto.getUsername().isBlank()) {
            user = userRepository.findByCredentialsUsername(credentialsRequestDto.getUsername()).orElse(null);
        }

        // If no user found yet, try finding by email if provided
        if (user == null && credentialsRequestDto.getEmail() != null && !credentialsRequestDto.getEmail().isBlank()) {
            user = userRepository.findByCredentialsEmail(credentialsRequestDto.getEmail()).orElse(null);
        }

        // If still no user found, throw authentication exception
        if (user == null) {
            throw new AuthenticationException(
                    "Invalid username, email or password",
                    "INVALID_CREDENTIALS",
                    "The provided username, email or password is incorrect"
            );
        }

        // Verify password matches stored password
        if (!user.getCredentials().getPassword().equals(credentialsRequestDto.getPassword())) {
            throw new AuthenticationException(
                    "Invalid username, email or password",
                    "INVALID_CREDENTIALS",
                    "The provided username, email or password is incorrect"
            );
        }

        // returns authenticated user
        return user;
    }

    /**
     * Handles user logout
     * Invalidates the current user session.
     */
    @Override
    public LogoutResponseDto logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Retrieve username before invalidating session
            String username = (String) session.getAttribute("username");

            session.invalidate();
            return new LogoutResponseDto(username + " logged out successfully", true);
        }

        return new LogoutResponseDto("No active session found", false);
    }

}
