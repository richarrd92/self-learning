package spring_boot.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.employee_management.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
