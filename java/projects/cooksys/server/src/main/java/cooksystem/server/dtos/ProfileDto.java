package cooksystem.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDto {
    private String first;
    private String last;
    private String phone;
}
