package cooksystem.server.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProjectRequestDto {
    private String name;
    private String description;
    private boolean active;
}
