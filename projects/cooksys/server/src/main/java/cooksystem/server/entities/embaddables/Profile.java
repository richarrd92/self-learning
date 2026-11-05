package cooksystem.server.entities.embaddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor // DataSeeder
@Getter
@Setter
public class Profile {
    private String first;
    private String last;
    private String phone;
}
