package com.example.JustBuy.service;

import com.example.JustBuy.dto.RequestDto.SellerRequest;
import com.example.JustBuy.dto.ResponseDto.SellerResponse;
import com.example.JustBuy.exception.DuplicateSellerException;
import org.springframework.stereotype.Service;


public interface SellerService {

    public SellerResponse addSeller(SellerRequest sellerRequest) throws DuplicateSellerException;
}
