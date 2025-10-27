package cooksystem.server.entities.embaddables;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class UserState {
    /**
     * Represents the current state of a user account.
     * Defaults for a new user created by an admin:
     *  * - active = false until the user logs in for the first time,
     *  *          and automatically set to false after e.g. 7 days of inactivity
     * - admin = false
     * - status = PENDING until the user logs in for the first time, then set to JOINED
     */
    private boolean active = false;
    private boolean admin = false;

    @Enumerated(EnumType.STRING) // Enforce String type
    private Status status = Status.PENDING;

    public enum Status{
        PENDING, JOINED
    }
}
