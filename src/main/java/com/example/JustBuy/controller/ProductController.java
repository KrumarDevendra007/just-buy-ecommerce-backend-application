package com.example.JustBuy.controller;

import com.example.JustBuy.dto.RequestDto.ProductRequestDto;
import com.example.JustBuy.dto.ResponseDto.ProductResponseDto;
import com.example.JustBuy.enums.ProductCategory;
import com.example.JustBuy.exception.InvalidSellerException;
import com.example.JustBuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {

        return productService.addProduct(productRequestDto);
    }

    // get all product of a particular category
    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category") ProductCategory category){
        return  productService.getAllProductsByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(@PathVariable("price") int price, @PathVariable("category") String productCategory){
        return productService.getAllProductsByPriceAndCategory(price, productCategory);
    }

    // get all product by seller emailId
    // delete a product by seller id and product id
    // return top 5 cheapest products
    // return top 5 costliest products
    // return all out-of-stock product
    // return all available product
    // return all product that have quantity less 5
    // return the cheapest product in a particular category
}
