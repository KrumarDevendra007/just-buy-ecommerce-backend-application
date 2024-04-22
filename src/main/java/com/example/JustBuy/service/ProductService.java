package com.example.JustBuy.service;


import com.example.JustBuy.dto.RequestDto.ProductRequestDto;
import com.example.JustBuy.dto.ResponseDto.ProductResponseDto;
import com.example.JustBuy.enums.ProductCategory;
import com.example.JustBuy.exception.InvalidSellerException;
import com.example.JustBuy.exception.OutOfStockException;
import com.example.JustBuy.model.Item;
import com.example.JustBuy.model.Ordered;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category);

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category);

    public  void decreaseProductQuantity(Item item) throws OutOfStockException;
}
