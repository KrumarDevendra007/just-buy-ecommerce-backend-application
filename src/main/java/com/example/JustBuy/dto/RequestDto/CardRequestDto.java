package com.example.JustBuy.dto.RequestDto;

import com.example.JustBuy.enums.CardType;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    int customerId;
    String cardNo;
    int cvv;
    Date expiryDate;
    CardType cardType;
}
