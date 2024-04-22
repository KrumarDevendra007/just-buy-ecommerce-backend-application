package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.CustomerRequestDto;
import com.example.JustBuy.dto.ResponseDto.CustomerResponseDto;
import com.example.JustBuy.exception.CustomerEmailAlReadyPresent;
import com.example.JustBuy.model.Cart;
import com.example.JustBuy.model.Customer;
import com.example.JustBuy.repository.CustomerRepository;
import com.example.JustBuy.service.CustomerService;
import com.example.JustBuy.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws CustomerEmailAlReadyPresent {

        if(customerRepository.findByEmailId(customerRequestDto.getEmailId()) != null){
            throw new CustomerEmailAlReadyPresent("Sorry! Customer already exists!!");
        }

        // request dto -> customer
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .numberOfItems(0)
                .totalCost(0)
                .customer(customer)
                . build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        // return response dto
        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);


    }
}
