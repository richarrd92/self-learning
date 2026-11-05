package cooksystem.server.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // many-to-one relationship with Company entity
    @ManyToOne
    @JoinColumn // defaults to company_id as foreign key
    private Company company;

    // inverse side of the user_team relationship
    @ManyToMany(mappedBy = "teams") // teams set in user entity
    private Set<User> users = new HashSet<>();

    // one-to-many relationship with Project entity
    // one Team can have many Projects
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();
}
