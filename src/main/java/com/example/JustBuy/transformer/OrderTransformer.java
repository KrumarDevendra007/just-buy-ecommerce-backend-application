package com.example.JustBuy.transformer;

import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.model.Ordered;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderTransformer {

    public static OrderResponseDto orderToOrderResponseDto(Ordered order){

        return OrderResponseDto
                .builder()
                .orderDate(order.getOrderDate())
                .usedCard(order.getUsedCard())
                .customerName(order.getCustomer().getName())
                .build();
    }

}
