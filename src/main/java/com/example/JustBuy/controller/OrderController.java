package com.example.JustBuy.controller;

import com.example.JustBuy.dto.RequestDto.ItemRequestDto;
import com.example.JustBuy.dto.RequestDto.OrderRequestDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public OrderResponseDto buyItem(@RequestBody OrderRequestDto orderRequestDto) throws Exception {

        return orderService.placeOrder(orderRequestDto);
    }

    // get all the order for a customer

    // get recent 5 order

    // delete an order from the order list

    // select the order and also tell the customer with the highest total value

}
