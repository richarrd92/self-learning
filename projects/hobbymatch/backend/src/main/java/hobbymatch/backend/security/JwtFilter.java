package hobbymatch.backend.security;

import hobbymatch.backend.exceptions.JwtException;
import hobbymatch.backend.services.JwtService;
import hobbymatch.backend.services.JwtUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom JWT authentication filter.
 * This filter intercepts every HTTP request, checks for a valid JWT token
 * in the "Authorization" header, validates it, and if valid — authenticates
 * the user by setting their details in the Spring Security context.
 * Extends OncePerRequestFilter so that it runs only once per request.
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final JwtUserService jwtUserService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            @NonNull HttpServletResponse httpServletResponse,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Skip JWT check for public endpoints
        String path = httpServletRequest.getServletPath();
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // Get Authorization authorizationHeader
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        // Missing or malformed token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw JwtException.missingToken();
        }

        // Extract JWT token and validate token
        final String token = authorizationHeader.substring(7);
        if (!jwtService.validateToken(token)) {
            throw JwtException.invalidOrExpired();
        }

        // Get userId from token
        final Long userId = jwtService.getUserIdFromToken(token);

        // Authenticate user if not already authenticated
        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from userId
            UserDetails userDetails = jwtUserService.loadUserById(userId);

            // Create authentication token with user details and authorities
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                    userDetails,                         // principal (user info)
                            null,                                   // credentials (password not needed here)
                            userDetails.getAuthorities()            // roles/permissions
            );

            // Attach request details (e.g., IP, session) to authentication token
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // Continue filter chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
