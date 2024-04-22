package com.example.JustBuy.controller;

import com.example.JustBuy.dto.RequestDto.CustomerRequestDto;
import com.example.JustBuy.dto.ResponseDto.CustomerResponseDto;
import com.example.JustBuy.exception.CustomerEmailAlReadyPresent;
import com.example.JustBuy.service.CustomerService;
import com.example.JustBuy.service.Implement.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws CustomerEmailAlReadyPresent, CustomerEmailAlReadyPresent {

        return customerService.addCustomer(customerRequestDto);

    }

    //view all customer
    // get a customers by email/mob
    // get all customer whose age is grater then 25
    // get all customer who use VISA card
    // update a customer info by email
    // delete a customer by email or mob

}
