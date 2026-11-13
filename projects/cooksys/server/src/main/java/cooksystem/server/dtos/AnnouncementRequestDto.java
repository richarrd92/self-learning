package cooksystem.server.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnnouncementRequestDto {
    private Long authorId;
    private String title;
    private String message;
}
