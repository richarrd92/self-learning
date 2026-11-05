package lemonadeStand.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class OrderResponseDto {

    List<LemonadeResponseDto> lemonades;
    CustomerResponseDto customer;
    LemonadeStandResponseDto lemonadeStand;
    private Long id;
    private double total;

}
