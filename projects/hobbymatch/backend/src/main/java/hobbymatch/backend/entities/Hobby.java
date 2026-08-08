package hobbymatch.backend.entities;

import hobbymatch.backend.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a hobby or interest that users can have.
 * Each hobby has a name, category, and a set of users associated with it.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hobby_table")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private boolean isDeleted = false;

    // Many-to-many relationship with User entity
    @ManyToMany(mappedBy = "hobbies")
    private List<User> users;
}
