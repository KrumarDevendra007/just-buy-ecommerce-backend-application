package com.example.JustBuy.service;


import com.example.JustBuy.dto.RequestDto.CustomerRequestDto;
import com.example.JustBuy.dto.ResponseDto.CustomerResponseDto;
import com.example.JustBuy.exception.CustomerEmailAlReadyPresent;
import org.springframework.stereotype.Service;



public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws CustomerEmailAlReadyPresent;
}
