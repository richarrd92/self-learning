package cooksystem.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CredentialsRequestDto {
    private String username;
    private String password;
    private String email;
}
