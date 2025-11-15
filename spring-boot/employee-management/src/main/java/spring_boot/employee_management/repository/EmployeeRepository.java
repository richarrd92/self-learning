package spring_boot.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.employee_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
