package lemonadeStand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LemonadeResponseDto {

    private Long id;

    private double lemonJuice;

    private double water;

    private double sugar;

    private int iceCubes;

    private double price;

}
