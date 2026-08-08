package hobbymatch.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


/**
 * Global CORS (Cross-Origin Resource Sharing) configuration for the backend.
 * CORS defines which frontend domains are allowed to make HTTP requests
 * (like GET, POST, PUT, DELETE) to this backend server.
 * This configuration ensures that requests from the Angular app (running
 * on <a href="http://localhost:4200">...</a> during development) are accepted.
 * Note: This is fine for development but **not recommended for production**
 * because it removes all cross-origin restrictions.
 */
@Configuration
public class CorsConfig {

    /**
     * Defines and registers the application's CORS rules.
     * This tells the backend which origins, methods, and headers are allowed
     * when the frontend makes API requests.
     * @return a CorsConfigurationSource object that Spring Security uses globally
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        // Create CORS configuration object
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow requests from the Angular development server
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));

        // Allow all HTTP methods
        configuration.setAllowedMethods(List.of("*")); // allowed HTTP methods

        // Allow all headers (e.g., Authorization, Content-Type)
        configuration.setAllowedHeaders(List.of("*"));

        // Allow credentials (e.g., cookies, authorization headers)
        configuration.setAllowCredentials(true);

        // Apply this CORS configuration to all API endpoints (/** means every path)
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);

        // Return the configured CORS urlBasedCorsConfigurationSource to Spring
        return urlBasedCorsConfigurationSource;
    }
}
