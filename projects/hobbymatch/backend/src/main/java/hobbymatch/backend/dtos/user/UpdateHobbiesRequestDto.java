package hobbymatch.backend.dtos.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * DTO for updating a user's hobbies.
 * Used as the request body when calling the API endpoint to change hobbies.
 */
@Data
public class UpdateHobbiesRequestDto {
    @NotEmpty(message = "At least one hobby must be selected")
    private List<Long> hobbies;
}
