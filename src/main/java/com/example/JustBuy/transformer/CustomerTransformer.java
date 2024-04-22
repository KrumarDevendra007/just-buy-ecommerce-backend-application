package com.example.JustBuy.transformer;


import com.example.JustBuy.dto.RequestDto.CustomerRequestDto;
import com.example.JustBuy.dto.ResponseDto.CustomerResponseDto;
import com.example.JustBuy.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

     public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

         return Customer.builder()
                 .name(customerRequestDto.getName())
                 .age(customerRequestDto.getAge())
                 .address(customerRequestDto.getAddress())
                 .emailId(customerRequestDto.getEmailId())
                 .mobileNo(customerRequestDto.getMobileNo())
                 .build();
     }

     public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){

         return CustomerResponseDto.builder()
                 .message("Congratulation " +customer.getName()+ " Wellcome to JustBuy!!!")
                 .build();
     }

}
