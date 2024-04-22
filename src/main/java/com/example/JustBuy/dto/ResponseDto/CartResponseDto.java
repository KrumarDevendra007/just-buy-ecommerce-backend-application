package com.example.JustBuy.dto.ResponseDto;

import com.example.JustBuy.model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    int cartTotal;
    int numberOfItems;
    String customerName;
    List<ItemResponseDto> items;

}
