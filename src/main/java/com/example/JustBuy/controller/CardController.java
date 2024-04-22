package com.example.JustBuy.controller;

import com.example.JustBuy.dto.RequestDto.CardRequestDto;
import com.example.JustBuy.dto.ResponseDto.CardResponseDto;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

     @Autowired
     CardService cardService;

     @PostMapping("/add")
     public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
           try{
                CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
                return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
           }
           catch (InvalidCustomerException e){
               return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
           }
     }

     // get all VISA cards
    // get all mastercard  cards whose expire is grater then 1 jan 2025
    // return the cardType which has maximum number of card present in db

}
