package com.example.JustBuy.service;

import com.example.JustBuy.dto.RequestDto.OrderRequestDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.exception.InvalidCardException;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.exception.InvalidProductException;
import com.example.JustBuy.exception.OutOfStockException;
import com.example.JustBuy.model.Card;
import com.example.JustBuy.model.Customer;
import com.example.JustBuy.model.Ordered;

public interface OrderService {

    public Ordered placeOrder(Customer customer, Card card) throws OutOfStockException;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
