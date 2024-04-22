package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.OrderRequestDto;
import com.example.JustBuy.dto.ResponseDto.ItemResponseDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.enums.ProductStatus;
import com.example.JustBuy.exception.InvalidCardException;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.exception.InvalidProductException;
import com.example.JustBuy.exception.OutOfStockException;
import com.example.JustBuy.model.*;
import com.example.JustBuy.repository.CardRepository;
import com.example.JustBuy.repository.CustomerRepository;
import com.example.JustBuy.repository.OrderedRepository;
import com.example.JustBuy.repository.ProductRepository;
import com.example.JustBuy.service.OrderService;
import com.example.JustBuy.service.ProductService;
import com.example.JustBuy.transformer.ItemTransformer;
import com.example.JustBuy.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Override
    public Ordered placeOrder(Customer customer, Card card) throws OutOfStockException {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskCard(card.getCardNo());
        order.setUsedCard(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItems()){
            try {
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }
            catch (Exception e){
                throw new OutOfStockException("Product Out of stock.");
            }
        }

        order.setItems(orderedItems);
        for(Item item : orderedItems){
            item.setOrder(order);
        }
        order.setTotalCost(cart.getTotalCost());

        return order;
    }


    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
          Customer customer;
          try {
              customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
          }
          catch (Exception e){
              throw new InvalidCustomerException("Customer Id is invalid");
          }

          Product product;
          try{
              product = productRepository.findById(orderRequestDto.getProductId()).get();
          }
          catch (Exception e){
              throw new InvalidProductException("Product doesn't exist");
          }

          Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());


        if(card == null || card.getCvv() != orderRequestDto.getCvv() || card.getCustomer() != customer
                || card.getCardNo().length() < 16){
            throw new InvalidCardException("Your card is not valid!!!");
        }



        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();

       try {
           productService.decreaseProductQuantity(item);
       }
       catch (Exception e){
           throw new Exception(e.getMessage());
       }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskCard(card.getCardNo());
        order.setUsedCard(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalCost(item.getRequiredQuantity() * product.getPrice());
        order.getItems().add(item);

        customer.getOrderedList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order);

        OrderResponseDto orderResponseDto = OrderTransformer.orderToOrderResponseDto(order);
        orderResponseDto.setOrderNo(savedOrder.getOrderNo());
        orderResponseDto.setTotalCost(savedOrder.getTotalCost());

        List<ItemResponseDto> itemsList = new ArrayList<>();
        for (Item itemEntity : savedOrder.getItems()){
            ItemResponseDto itemResponseDto = ItemTransformer.itemToItemResponseDto(itemEntity);

            itemsList.add(itemResponseDto);
        }

        orderResponseDto.setItems(itemsList);

        return orderResponseDto;

    }

    // Function for generate MaskCard
    public String generateMaskCard(String cardNo){
        String maskedCardNo = "";
        for(int i=0; i<cardNo.length()-4; i++){
            maskedCardNo += "X";
        }
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;
    }
}
