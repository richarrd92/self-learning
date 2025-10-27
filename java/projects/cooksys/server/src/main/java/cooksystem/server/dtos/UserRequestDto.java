package cooksystem.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private CredentialsRequestDto credentials;
    private ProfileDto profile;
    private UserStateDto userState;
}
