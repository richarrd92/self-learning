package cooksystem.server.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private Set<TeamDto> teams;
    private Set<UserRequestDto> users;
}
