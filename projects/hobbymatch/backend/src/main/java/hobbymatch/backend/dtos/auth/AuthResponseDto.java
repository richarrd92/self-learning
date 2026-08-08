package hobbymatch.backend.dtos.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for general responses after a successful user authentication.
 * Contains the generated JWT token, user info, etc.
 */
@Getter
@Setter
public class AuthResponseDto {
    private String token;
    private Long userId;
    private String name;
    private String username;
}
