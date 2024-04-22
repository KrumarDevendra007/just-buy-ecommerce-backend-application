package com.example.JustBuy.transformer;

import com.example.JustBuy.dto.RequestDto.SellerRequest;
import com.example.JustBuy.dto.ResponseDto.SellerResponse;
import com.example.JustBuy.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {

    // Static because there is parameter and we con call it without object
    public static Seller sellerRequestDtoToSeller(SellerRequest sellerRequest){

        return  Seller.builder()
                .name(sellerRequest.getName())
                .emailId(sellerRequest.getEmailId())
                .age(sellerRequest.getAge())
                .mobileNo(sellerRequest.getMobileNo())
                .build();
    }

    public static SellerResponse sellerToSellerResponseDto(Seller seller){

        return SellerResponse.builder()
                .massage(seller.getName() + " congratulations you has been added as seller!!!")
                .build();
    }


}
