package cooksystem.server.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class AnnouncementDto {
    private Long id;
    private Timestamp date;
    private String title;
    private String message;
    private UserRequestDto author;
    private CompanyDto company;
}
