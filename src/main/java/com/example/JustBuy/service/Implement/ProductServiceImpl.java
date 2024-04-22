package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.ProductRequestDto;
import com.example.JustBuy.dto.ResponseDto.ProductResponseDto;
import com.example.JustBuy.enums.ProductCategory;
import com.example.JustBuy.enums.ProductStatus;
import com.example.JustBuy.exception.InvalidSellerException;
import com.example.JustBuy.exception.OutOfStockException;
import com.example.JustBuy.model.Item;
import com.example.JustBuy.model.Ordered;
import com.example.JustBuy.model.Product;
import com.example.JustBuy.model.Seller;
import com.example.JustBuy.repository.ProductRepository;
import com.example.JustBuy.repository.SellerRepository;
import com.example.JustBuy.service.ProductService;
import com.example.JustBuy.transformer.ProductTransformer;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
           Seller seller;
           try{
                seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
           }
           catch (Exception e){
               throw new InvalidSellerException("Seller does't exist");
           }

        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        // add product to product list
        seller.getProductList().add(product);
        sellerRepository.save(seller);

        // prepare response DTO
        return ProductTransformer.productToProductResponceDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category) {

           List<Product> products = productRepository.findByProductCategory(category);

           List<ProductResponseDto> productResponselist = new ArrayList<>();

           for(Product product : products){
               productResponselist.add(ProductTransformer.productToProductResponceDto(product));
           }

           return productResponselist;
    }

    @Override
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String productCategory) {

        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price,productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.productToProductResponceDto(product));
        }

        return productResponseDtos;
    }

    @Override
    public void decreaseProductQuantity(Item item) throws OutOfStockException {


             Product product = item.getProduct();
             int quantity = item.getRequiredQuantity();
             int currentQuantity = product.getQuantity();
             if(quantity > currentQuantity){
                 throw new OutOfStockException("Product Out of Stock");
             }
             product.setQuantity(currentQuantity-quantity);
             if(product.getQuantity() == 0){
                 product.setProductStatus(ProductStatus.OUT_OF_STOCK);
             }

    }
}
