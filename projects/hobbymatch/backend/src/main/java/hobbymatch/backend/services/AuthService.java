package hobbymatch.backend.services;

import hobbymatch.backend.dtos.auth.*;

/**
 * Service interface for authentication-related operations.
 * Defines the contract for registering and logging in users.
 */
public interface AuthService {
    AuthResponseDto register(RegisterRequestDto registerRequestDto);
    AuthResponseDto login(LoginRequestDto loginRequestDto);
}
