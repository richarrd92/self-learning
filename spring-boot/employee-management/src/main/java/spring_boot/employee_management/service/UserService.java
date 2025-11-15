package spring_boot.employee_management.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_boot.employee_management.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    void save(UserRegistrationDto userRegistrationDto);
}
