package hobbymatch.backend.dtos.auth;

import lombok.Data;

/**
 * DTO for user registration requests.
 * Contains the user’s name and chosen password.
 */
@Data
public class RegisterRequestDto {
    private String name;
    private String password;
}
