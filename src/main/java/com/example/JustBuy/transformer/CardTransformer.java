package com.example.JustBuy.transformer;

import com.example.JustBuy.dto.RequestDto.CardRequestDto;
import com.example.JustBuy.dto.ResponseDto.CardResponseDto;
import com.example.JustBuy.model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){

          return Card.builder()
                  .cardNo(cardRequestDto.getCardNo())
                  .cardType(cardRequestDto.getCardType())
                  .cvv(cardRequestDto.getCvv())
                  .expiryDate(cardRequestDto.getExpiryDate())
                  .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card){

        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .build();
    }
}
