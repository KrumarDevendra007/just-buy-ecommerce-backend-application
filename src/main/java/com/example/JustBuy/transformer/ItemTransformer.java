package com.example.JustBuy.transformer;

import com.example.JustBuy.dto.RequestDto.ItemRequestDto;
import com.example.JustBuy.dto.ResponseDto.ItemResponseDto;
import com.example.JustBuy.model.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTransformer {

    public static Item itemRequestDtoToItem(ItemRequestDto itemRequestDto){
                return Item.builder()
                        .requiredQuantity(itemRequestDto.getRequiredQuantity())
                        .build();
    }

    public static ItemResponseDto itemToItemResponseDto(Item item){

              return ItemResponseDto.builder()
                      .priceOfOneItem(item.getProduct().getPrice())
                      .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                      .productName(item.getProduct().getName())
                      .quantity(item.getRequiredQuantity())
                      .build();
    }

}
