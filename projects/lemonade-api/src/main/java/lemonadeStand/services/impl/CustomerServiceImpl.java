package lemonadeStand.services.impl;

import lemonadeStand.entities.Customer;
import lemonadeStand.exceptions.BadRequestException;
import lemonadeStand.exceptions.NotFoundException;
import lemonadeStand.mappers.CustomerMapper;
import lemonadeStand.model.CustomerRequestDto;
import lemonadeStand.model.CustomerResponseDto;
import lemonadeStand.repositories.CustomerRepository;
import lemonadeStand.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private void validateCustomerRequest(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null || customerRequestDto.getName() == null
                || customerRequestDto.getPhoneNumber() == null) {
            throw new BadRequestException("All fields are required on a customer request dto");
        }
    }

    @Override
    public Customer getCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("No customer with id: " + id);
        }
        return optionalCustomer.get();
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        validateCustomerRequest(customerRequestDto);
        return customerMapper.entityToResponseDto(
                customerRepository.saveAndFlush(customerMapper.requestDtoToEntity(customerRequestDto)));
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerMapper.entitiesToResponseDtos(customerRepository.findAll());
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        return customerMapper.entityToResponseDto(getCustomer(id));
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null) {
            throw new BadRequestException("At least 1 field must be sent to update");
        }
        Customer customerToUpdate = getCustomer(id);
        if (customerRequestDto.getName() != null) {
            customerToUpdate.setName(customerRequestDto.getName());
        }
        if (customerRequestDto.getPhoneNumber() != null) {
            customerToUpdate.setPhoneNumber(customerRequestDto.getPhoneNumber());
        }
        return customerMapper.entityToResponseDto(customerRepository.saveAndFlush(customerToUpdate));
    }

    @Override
    public CustomerResponseDto deleteCustomer(Long id) {
        Customer customerToDelete = getCustomer(id);
        customerRepository.delete(customerToDelete);
        return customerMapper.entityToResponseDto(customerToDelete);
    }

}
