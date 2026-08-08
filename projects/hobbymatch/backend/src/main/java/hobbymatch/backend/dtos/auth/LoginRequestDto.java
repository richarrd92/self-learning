package hobbymatch.backend.dtos.auth;

import lombok.Data;

/**
 * DTO for user login requests.
 * Contains the credentials required to authenticate a user.
 */
@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
