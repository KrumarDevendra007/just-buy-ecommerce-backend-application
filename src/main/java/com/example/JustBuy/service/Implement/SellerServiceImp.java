package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.SellerRequest;
import com.example.JustBuy.dto.ResponseDto.SellerResponse;
import com.example.JustBuy.exception.DuplicateSellerException;
import com.example.JustBuy.model.Seller;
import com.example.JustBuy.repository.SellerRepository;
import com.example.JustBuy.service.SellerService;
import com.example.JustBuy.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponse addSeller(SellerRequest sellerRequest) throws DuplicateSellerException {


//        Normal Way
//        Seller seller = new Seller();
//        seller.setName(sellerRequest.getName());
//        seller.setEmailId(sellerRequest.getEmailId());
//        seller.setMobileNo(sellerRequest.getMobileNo());
//        seller.setAge(sellerRequest.getAge());

         if(sellerRepository.findByEmailId(sellerRequest.getEmailId()) != null){
             throw  new DuplicateSellerException("Seller emailid already registed");
         }

        // Use transformer
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequest);
        Seller savedSeller = sellerRepository.save(seller);

         SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponseDto(savedSeller);
         return  sellerResponse;

    }
}
