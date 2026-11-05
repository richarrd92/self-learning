package lemonadeStand.mappers;

import lemonadeStand.entities.Order;
import lemonadeStand.model.OrderRequestDto;
import lemonadeStand.model.OrderResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, LemonadeMapper.class, LemonadeStandMapper.class})
public interface OrderMapper {

    Order requestDtoToEntity(OrderRequestDto orderRequestDto);

    OrderResponseDto entityToResponseDto(Order order);

    List<OrderResponseDto> entitiesToResponseDtos(List<Order> orders);

}
