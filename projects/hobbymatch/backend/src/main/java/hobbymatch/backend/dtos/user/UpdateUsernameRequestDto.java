package hobbymatch.backend.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for updating a user's username.
 * Used as the request body when calling the API endpoint to change username.
 */
@Data
public class UpdateUsernameRequestDto {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be 3–20 characters long")
    private String username;
}
