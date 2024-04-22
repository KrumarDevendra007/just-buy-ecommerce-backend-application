package com.example.JustBuy.repository;

import com.example.JustBuy.dto.ResponseDto.ProductResponseDto;
import com.example.JustBuy.enums.ProductCategory;
import com.example.JustBuy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);

    @Query(value = "select * from product p where p.price > :price and p.product_category = :category", nativeQuery = true)
    List<Product>getAllProductsByPriceAndCategory(int price, String category);

    //  query without nativeQuery (Enum can be used in this query)
//    @Query(value = "select p from product p where p.price > :price and p.product_category = :category")
//    List<Product>getAllProductsByPriceAndCategory(int price, ProductCategory category);
}
