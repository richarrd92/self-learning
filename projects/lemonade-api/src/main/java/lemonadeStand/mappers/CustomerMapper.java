package lemonadeStand.mappers;

import lemonadeStand.entities.Customer;
import lemonadeStand.model.CustomerDto;
import lemonadeStand.model.CustomerRequestDto;
import lemonadeStand.model.CustomerResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer requestDtoToEntity(CustomerRequestDto customerRequestDto);

    Customer customerDtoToEntity(CustomerDto customerDto);

    CustomerResponseDto entityToResponseDto(Customer customer);

    List<CustomerResponseDto> entitiesToResponseDtos(List<Customer> customers);

}
