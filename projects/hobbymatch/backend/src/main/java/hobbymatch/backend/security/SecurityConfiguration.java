package hobbymatch.backend.security;

import hobbymatch.backend.exceptions.UserException;
import hobbymatch.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration for the backend.
 * Responsibilities:
 * - Configure authentication and authorization rules
 * - Define beans for UserDetailsService, PasswordEncoder, SecurityFilterChain and AuthenticationManager
 * - Set up HTTP security and JWT filter
 * NOTE: Later, enable method-level security annotations (@PreAuthorize, @Secured)
 *       by adding @EnableMethodSecurity(prePostEnabled = true) to this class or a separate config.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // TODO: enable method-level security annotations
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;
    private final UserRepository userRepository;

    /**
     * Defines how to load user information (username, password, roles) from the database
     * when user tries to log in or authenticate.
     * Spring Security will call this automatically whenever it needs to verify a user.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Build and return UserDetailsService object
        return username -> userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().name()) // assign user's role for authorization
                        .build()
                ).orElseThrow(() -> UserException.notFound("User not found"));
    }

    /**
     * Provides a password encoder that uses BCrypt hashing.
     * This ensures that stored passwords are securely hashed (not plain text),
     * and that incoming login passwords are properly verified.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes an AuthenticationManager bean, which handles authentication logic
     * (such as comparing a user's credentials with database records).
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures global security rules for HTTP requests.
     * - Disables CSRF since currently using JWT (stateless, token-based authentication).
     * - Allows public access to /register and /login endpoints.
     * - Requires authentication for all other routes.
     * - Enforces stateless sessions (no server-side session tracking at the moment).
     * - Adds a custom JWT filter before the default authentication filter,
     *   so incoming tokens are validated before any other security processing.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for stateless API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // public endpoints
                        .anyRequest().authenticated()) // all others need authentication
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless sessions
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // JwtFilter -> UsernamePasswordAuthenticationFilter
                .build();
    }
}