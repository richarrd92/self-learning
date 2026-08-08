package hobbymatch.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a geographic location.
 * Stores name and coordinates (latitude & longitude).
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "location_table")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    private String name;
    private Double latitude;
    private Double longitude;
}
