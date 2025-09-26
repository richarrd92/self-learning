package lemonadeStand.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class OrderRequestDto {

    private List<LemoandeDto> lemonades;

    private CustomerDto customer;

    private LemonadeStandDto lemonadeStand;

}
