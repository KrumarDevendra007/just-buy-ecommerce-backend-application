package com.example.JustBuy.service;

import com.example.JustBuy.dto.RequestDto.CheckOutCartRequestDto;
import com.example.JustBuy.dto.ResponseDto.CartResponseDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.exception.InvalidCardException;
import com.example.JustBuy.exception.InvalidCartException;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.exception.OutOfStockException;
import com.example.JustBuy.model.Item;

public interface CartService {

    public CartResponseDto saveCart(int customerId, Item item);

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception;

}
