package lemonadeStand.services.impl;

import lemonadeStand.entities.Lemonade;
import lemonadeStand.entities.Order;
import lemonadeStand.mappers.OrderMapper;
import lemonadeStand.model.OrderRequestDto;
import lemonadeStand.model.OrderResponseDto;
import lemonadeStand.repositories.OrderRepository;
import lemonadeStand.services.CustomerService;
import lemonadeStand.services.LemonadeService;
import lemonadeStand.services.LemonadeStandService;
import lemonadeStand.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final LemonadeService lemonadeService;
    private final CustomerService customerService;
    private final LemonadeStandService lemonadeStandService;

    private final OrderMapper orderMapper;

    private void setupOrder(Order order) {
        double total = 0.0;
        List<Lemonade> lemonades = new ArrayList<>();
        for (Lemonade lemonade : order.getLemonades()) {
            lemonade = lemonadeService.getLemonade(lemonade.getId());
            lemonades.add(lemonade);
            total += lemonade.getPrice();
        }
        order.setLemonades(lemonades);
        order.setTotal(total);
        order.setCustomer(customerService.getCustomer(order.getCustomer().getId()));
        order.setLemonadeStand(lemonadeStandService.getLemonadeStand(order.getLemonadeStand().getId()));
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.requestDtoToEntity(orderRequestDto);
        setupOrder(order);
        return orderMapper.entityToResponseDto(orderRepository.saveAndFlush(order));
    }

}
