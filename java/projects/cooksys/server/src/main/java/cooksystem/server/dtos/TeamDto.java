package cooksystem.server.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class TeamDto {
    private Long id;
    private String name;
    private String description;
    private Set<UserRequestDto> users;
}
