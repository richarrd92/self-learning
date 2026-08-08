package hobbymatch.backend.services.implementations;

import hobbymatch.backend.dtos.auth.AuthResponseDto;
import hobbymatch.backend.dtos.auth.LoginRequestDto;
import hobbymatch.backend.dtos.auth.RegisterRequestDto;
import hobbymatch.backend.entities.User;
import hobbymatch.backend.exceptions.UserException;
import hobbymatch.backend.mappers.AuthMapper;
import hobbymatch.backend.repositories.UserRepository;
import hobbymatch.backend.services.AuthService;
import hobbymatch.backend.services.JwtService;
import hobbymatch.backend.services.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service implementation for authentication-related operations.
 * Handles user registration, login, password validation, and JWT token generation.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    private final AuthMapper authMapper;
    private final JwtService jwtService;

    /**
     * Registers a new user.
     *
     * @param registerRequestDto contains name and password
     * @return AuthResponseDto with user info and JWT token
     */
    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {
        // Validate name
        if(!validationService.isNameValid(registerRequestDto.getName())) {
            throw UserException.invalidInput("Invalid name format.");
        }

        // Validate password
        if (!validationService.isPasswordValid(registerRequestDto.getPassword())) {
            throw UserException.invalidInput("Weak password.");
        }

        // Assign a temporary username
        String tempUsername;
        do {
            tempUsername = "user" + UUID.randomUUID().toString().substring(0, 8);
            System.out.println("temp username created: " + tempUsername);
        } while (userRepository.findByUsername(tempUsername).isPresent());

        // Create and save new user in DB
        User newUser = new User();
        newUser.setUsername(tempUsername); // Assign temporary username
        newUser.setName(registerRequestDto.getName());
        newUser.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        User savedUser = userRepository.save(newUser);

        // generate JWT token
        String token = jwtService.generateToken(savedUser.getUserId());

        // Return response DTO
        AuthResponseDto authResponseDto = authMapper.toAuthDto(savedUser);
        authResponseDto.setToken(token);
        return authResponseDto;
    }

    /**
     * Logs in an existing user.
     *
     * @param loginRequestDto contains username and password
     * @return AuthResponseDto with user info and JWT token
     */
    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        // Find user by username
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> UserException.invalidInput("Invalid username or password"));

        // Verify password
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw UserException.invalidInput("Invalid username or password");
        }

        // Generate JWT token
        String token = jwtService.generateToken(user.getUserId());

        // Return response DTO
        AuthResponseDto authResponseDto = authMapper.toAuthDto(user);
        authResponseDto.setToken(token);
        return authResponseDto;
    }
}
