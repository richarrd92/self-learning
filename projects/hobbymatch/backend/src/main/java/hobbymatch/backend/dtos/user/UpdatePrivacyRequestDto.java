package hobbymatch.backend.dtos.user;

import lombok.Data;

/**
 * DTO for updating a user's profile privacy.
 * Used as the request body when calling the API endpoint to change profile privacy.
 */
@Data
public class UpdatePrivacyRequestDto {
    private boolean isPublic;
}
