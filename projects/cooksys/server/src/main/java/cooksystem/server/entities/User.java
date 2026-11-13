package cooksystem.server.entities;

import cooksystem.server.entities.embaddables.Credentials;
import cooksystem.server.entities.embaddables.Profile;
import cooksystem.server.entities.embaddables.UserState;
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
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "active", column = @Column(name = "active")),
            @AttributeOverride(name = "admin", column = @Column(name = "admin")),
            @AttributeOverride(name = "status", column = @Column(name = "status"))
    }) // do not override column names in database
    /**
     * Embeds the UserState with default values.
     * - active = false until first login, then may reset after inactivity
     * - admin = false
     * - status = PENDING until first login, then JOINED
     */
    private UserState userState = new UserState();

    // many-to-many relationship with Team entity
    // user can be in multiple teams
    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    // many-to-many relationship with Company entity
    // user can be in multiple companies
    @ManyToMany
    @JoinTable(
            name = "user_company",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private Set<Company> companies = new HashSet<>();

    // one-to-many relationship with Announcements entity
    // one User can have many Announcements
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Announcement> announcements = new ArrayList<>();
}
