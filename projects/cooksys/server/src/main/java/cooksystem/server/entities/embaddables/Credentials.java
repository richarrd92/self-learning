package cooksystem.server.entities.embaddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor // DataSeeder
@Getter
@Setter
public class Credentials {
    private String username;
    private String password;
    private String email;

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Credentials otherCredentials = (Credentials) other;
        return Objects.equals(username, otherCredentials.username)
                && Objects.equals(email, otherCredentials.email)
                && Objects.equals(password, otherCredentials.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(username, email, password);
    }
}
