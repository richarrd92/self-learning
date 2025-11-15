package spring_boot.employee_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @link Configuration: Indicates that this class contains Spring configuration.
 * @link EnableWebSecurity: Enables Spring Security's web security support and provides Spring MVC integration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     *
     * @link SecurityFilterChain: Defines the security filter chain.
     * @param httpSecurity Allows configuring web-based security for specific http requests.
     *
     * @link authorizeHttpRequests: Configures authorization for HTTP requests.
     * @link requestMatchers: Specifies the URL patterns that should be permitted without authentication.
     * @link anyRequest().authenticated(): Requires authentication for any other requests.
     * @link formLogin: Configures form-based authentication.
     *      loginPage("/login"): Specifies the custom login page URL.
     *      permitAll(): Allows access to the login page for all users.
     * @link logout: Configures logout functionality.
     *      logoutUrl("/logout"): Specifies the logout URL.
     *      logoutSuccessUrl("/login?logout"): Redirects to the login page with a logout message after logout.
     *      permitAll(): Allows access to the logout functionality for all users.
     *
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration**", "/js/**", "/css/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                ).build();
    }

    /**
     * configureGlobal: Configures the global authentication manager.
     * userDetailsService: Sets the custom UserDetailsService for authentication.
     * bCryptPasswordEncoder: Sets the BCryptPasswordEncoder for encoding passwords.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
                                UserDetailsService userDetailsService,
                                BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

}
