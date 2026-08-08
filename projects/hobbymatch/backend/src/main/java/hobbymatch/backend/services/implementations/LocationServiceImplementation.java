package hobbymatch.backend.services.implementations;

import hobbymatch.backend.entities.Location;
import hobbymatch.backend.entities.embaddables.EmbeddedLocation;
import hobbymatch.backend.exceptions.LocationException;
import hobbymatch.backend.repositories.LocationRepository;
import hobbymatch.backend.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Service implementation for handling location data.
 * Validates user input, checks database cache, calls external Nominatim API for geocoding,
 * and returns embedded location data for use in user entities.
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImplementation implements LocationService {
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";
    private final LocationRepository locationRepository;

    /**
     * Get an embedded location from user input.
     * Steps:
     * 1. Validate input
     * 2. Check database cache
     * 3. Call Nominatim API if not in cache
     * 4. Save new location to database
     * 5. Return embedded location object
     * @param locationInput user-provided location string
     * @return EmbeddedLocation containing name, latitude, and longitude
     */
    @Override
    public EmbeddedLocation getLocation(String locationInput) {
        // Validate location
        if (locationInput == null || locationInput.isBlank()) {
            throw LocationException.invalidInput("Location cannot be null or blank");
        }

        // Check if location already exists in database
        Location existing = locationRepository.findByName(locationInput.toUpperCase());
        if (existing != null) {
            EmbeddedLocation cached = new EmbeddedLocation();
            cached.setLocationName(existing.getName());
            cached.setLatitude(existing.getLatitude());
            cached.setLongitude(existing.getLongitude());
            return cached;
        }

        // Build API request to Nominatim
        URI nominationUrl = UriComponentsBuilder
                .fromUriString(NOMINATIM_URL)
                .queryParam("q", locationInput) // search query
                .queryParam("format", "json")   // JSON response
                .queryParam("limit", 1)         // only top result
                .build()
                .toUri();

        // Call Nominatim API (HTTP request)
        RestTemplate restTemplate = new RestTemplate();
        NominatimResponse[] response = restTemplate.getForObject(nominationUrl, NominatimResponse[].class);

        // Throw exception if API returned no results
        if (response == null || response.length == 0) {
            throw LocationException.notFound("Location not found for: " + locationInput);
        }

        // Extract the first result
        NominatimResponse nominatimResponse = response[0];

        // Save new location to database (cache) using frontend input as name
        Location location = new Location();
        location.setName(locationInput.toUpperCase());
        location.setLatitude(Double.parseDouble(nominatimResponse.lat));
        location.setLongitude(Double.parseDouble(nominatimResponse.lon));
        locationRepository.save(location);

        // Map saved location to EmbeddedLocation for user entity
        EmbeddedLocation embeddedLocation = new EmbeddedLocation();
        embeddedLocation.setLocationName(location.getName());
        embeddedLocation.setLatitude(location.getLatitude());
        embeddedLocation.setLongitude(location.getLongitude());

        return embeddedLocation;
    }

    /**
     * Helper class to map Nominatim API JSON response.
     * Only lat/lon are used; display_name is ignored since we store frontend input.
     */
    private static class NominatimResponse {
        public String display_name; // ignored
        public String lat;          // latitude
        public String lon;          // longitude
    }
}
