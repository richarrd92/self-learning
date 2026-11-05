package cooksystem.server.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class TeamRequestDto {
    private Long authorId;
    private String name;
    private String description;
    private Set<Long> userIds;
}
