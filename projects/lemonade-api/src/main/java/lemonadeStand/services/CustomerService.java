package lemonadeStand.services;

import lemonadeStand.entities.Customer;
import lemonadeStand.model.CustomerRequestDto;
import lemonadeStand.model.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    Customer getCustomer(Long id);

    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto);

    CustomerResponseDto deleteCustomer(Long id);

}
