package lemonadeStand.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerResponseDto {

    private Long id;

    private String name;

    private String phoneNumber;

}
