package cooksystem.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CredentialsResponseDto {
    private Long id;
    private String username;
    private String email;
    private boolean admin;
    private Long companyId; // Company ID for non-admin users (first company they belong to)
}
