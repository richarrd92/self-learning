package hobbymatch.backend.dtos.user;

import hobbymatch.backend.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for updating a user's gender.
 */
@Data
public class UpdateGenderRequestDto {
    @NotNull(message = "Gender must be provided")
    private Gender gender;
}
