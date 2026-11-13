package cooksystem.server.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp date;

    private String title;
    private String message;

    // many-to-one relationship with Company entity
    @ManyToOne
    @JoinColumn // defaults to company_id as foreign key
    private Company company;

    // many-to-one relationship with User entity
    @ManyToOne
    @JoinColumn // defaults to author_id as foreign key
    private User author;
}
