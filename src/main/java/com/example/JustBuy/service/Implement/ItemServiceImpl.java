package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.CheckOutCartRequestDto;
import com.example.JustBuy.dto.RequestDto.ItemRequestDto;
import com.example.JustBuy.dto.ResponseDto.CartResponseDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.enums.ProductStatus;
import com.example.JustBuy.exception.*;
import com.example.JustBuy.model.*;
import com.example.JustBuy.repository.CardRepository;
import com.example.JustBuy.repository.CustomerRepository;
import com.example.JustBuy.repository.ItemRepository;
import com.example.JustBuy.repository.ProductRepository;
import com.example.JustBuy.service.ItemService;
import com.example.JustBuy.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.UUID;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, InvalidProductException, OutOfStockException {

        Customer customer;
        try {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("CustomerId is invalid!!!");
        }

        Product product;

        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e){
             throw new InvalidProductException("Product dosen't exist.");
        }

        if(itemRequestDto.getRequiredQuantity() > product.getQuantity() || product.getProductStatus() != ProductStatus.AVAILABLE){
            throw new OutOfStockException("Product out of stock.");
        }

        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto);
        item.setCart(customer.getCart());
        item.setProduct(product);
        product.getItemList().add(item);

        Product savedProduct = productRepository.save(product);

        // latest item at the last index of item list
        int size = product.getItemList().size();

        return  savedProduct.getItemList().get(size-1);
    }
}
