package cooksystem.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private UserCredentialsDto credentials;
    private ProfileDto profile;
    private UserStateDto userState;
    // TODO: Add TeamDto and CompanyDto?
}
