package lemonadeStand.services;

import lemonadeStand.model.OrderRequestDto;
import lemonadeStand.model.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

}
