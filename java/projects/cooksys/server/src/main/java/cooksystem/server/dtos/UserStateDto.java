package cooksystem.server.dtos;

import cooksystem.server.entities.embaddables.UserState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserStateDto {
    private boolean active;
    private boolean admin;
    private UserState.Status status; // PENDING, JOINED
}
