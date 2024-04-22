package com.example.JustBuy.dto.ResponseDto;


import com.example.JustBuy.model.Customer;
import com.example.JustBuy.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    Date orderDate;
    int totalCost;
    String orderNo;
    String usedCard;
    List<ItemResponseDto> items = new ArrayList<>();
    String customerName;
}
