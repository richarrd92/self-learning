package lemonadeStand.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerRequestDto {

    private String name;

    private String phoneNumber;

}
