package hobbymatch.backend.dtos.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for updating a user's bio.
 * Used as the request body when calling the API endpoint to change bio.
 */
@Data
public class UpdateBioRequestDto {
    @Size(max = 255, message = "Bio must not exceed 255 characters")
    private String bio;
}
