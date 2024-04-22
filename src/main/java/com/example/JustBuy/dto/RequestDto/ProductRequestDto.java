package com.example.JustBuy.dto.RequestDto;

import com.example.JustBuy.enums.ProductCategory;
import com.example.JustBuy.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    int sellerId;
    String productName;
    int price;
    int quantity;
    ProductCategory productCategory;

}
