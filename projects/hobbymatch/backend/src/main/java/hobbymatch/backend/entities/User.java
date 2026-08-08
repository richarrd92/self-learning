package hobbymatch.backend.entities;

import hobbymatch.backend.entities.embaddables.EmbeddedLocation;
import hobbymatch.backend.enums.Gender;
import hobbymatch.backend.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user in the system.
 * Contains personal info, authentication details, and relationships with hobbies.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    private String bio;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;
    private boolean isActive = true;
    private boolean isPublic = true;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Embedded
    private EmbeddedLocation embeddedLocation;

    @Column(nullable = false)
    private boolean isProfileComplete = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Many-to-many relation with hobbies
    @ManyToMany
    @JoinTable(
            name = "user_hobbies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private List<Hobby> hobbies;

    // Auto-set timestamps before persisting
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    // Auto-update timestamp on entity update
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
