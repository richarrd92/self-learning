package cooksystem.server.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // default value
    private boolean active = false;

    // many-to-one relationship with Team entity
    @ManyToOne
    @JoinColumn // defaults to team_id as foreign key
    private Team team;
}
