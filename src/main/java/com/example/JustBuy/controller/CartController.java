package com.example.JustBuy.controller;

import com.example.JustBuy.dto.RequestDto.CheckOutCartRequestDto;
import com.example.JustBuy.dto.RequestDto.ItemRequestDto;
import com.example.JustBuy.dto.ResponseDto.CartResponseDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.exception.InvalidCardException;
import com.example.JustBuy.exception.InvalidCartException;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.model.Item;
import com.example.JustBuy.service.CartService;
import com.example.JustBuy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try{
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(), savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
              return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {

        return  cartService.checkOutCart(checkOutCartRequestDto);
    }

    //remove from cart

    // view all item in cart

    // send email when order place
}
