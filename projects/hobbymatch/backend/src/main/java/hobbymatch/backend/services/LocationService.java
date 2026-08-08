package hobbymatch.backend.services;

import hobbymatch.backend.entities.embaddables.EmbeddedLocation;

/**
 * Service interface for handling location-related operations.
 * Provides methods to retrieve geographic coordinates for a given location.
 */
public interface LocationService {
    EmbeddedLocation getLocation(String cityName);
}
