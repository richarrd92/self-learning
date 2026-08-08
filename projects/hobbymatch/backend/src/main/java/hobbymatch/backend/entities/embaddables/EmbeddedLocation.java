package hobbymatch.backend.entities.embaddables;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a location embedded within User entity.
 * This avoids creating a separate table for simple location data.
 */
@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class EmbeddedLocation {
    private String locationName;
    private Double latitude;
    private Double longitude;
}
