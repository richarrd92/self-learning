package hobbymatch.backend.dtos.hobby;

import hobbymatch.backend.enums.Category;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for returning hobby information in API responses.
 * Contains basic hobby details including ID, name, and category.
 */
@Data
@Builder
public class HobbyResponseDto {
    private Long hobbyId;
    private String name;
    private Category category;
}
