package library_management.library;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private boolean borrowed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy; // user who borrowed book
}
