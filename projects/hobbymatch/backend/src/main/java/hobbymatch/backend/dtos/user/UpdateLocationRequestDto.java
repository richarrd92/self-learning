package hobbymatch.backend.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for updating a user's location.
 * Used as the request body when calling the API endpoint to change location.
 */
@Data
public class UpdateLocationRequestDto {
    @NotBlank(message = "Location cannot be blank")
    private String location;
}
