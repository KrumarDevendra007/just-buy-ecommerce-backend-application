package com.example.JustBuy.controller;

import com.example.JustBuy.dto.RequestDto.SellerRequest;
import com.example.JustBuy.dto.ResponseDto.SellerResponse;
import com.example.JustBuy.exception.DuplicateSellerException;
import com.example.JustBuy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

     @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest) throws DuplicateSellerException {

        try {
            SellerResponse sellerResponse = sellerService.addSeller(sellerRequest);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}


        // Get a seller by email

       // Get by id

     // get all seller

     // update seller info based on email id

     // delete a seller by email

    // delete seller by ID

   // get all seller of particular age