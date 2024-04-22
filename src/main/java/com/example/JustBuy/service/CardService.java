package com.example.JustBuy.service;

import com.example.JustBuy.dto.RequestDto.CardRequestDto;
import com.example.JustBuy.dto.ResponseDto.CardResponseDto;
import com.example.JustBuy.exception.InvalidCustomerException;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
