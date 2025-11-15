package spring_boot.employee_management.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role_table")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
