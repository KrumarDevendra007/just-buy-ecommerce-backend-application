package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.CheckOutCartRequestDto;
import com.example.JustBuy.dto.ResponseDto.CartResponseDto;
import com.example.JustBuy.dto.ResponseDto.ItemResponseDto;
import com.example.JustBuy.dto.ResponseDto.OrderResponseDto;
import com.example.JustBuy.exception.InvalidCardException;
import com.example.JustBuy.exception.InvalidCartException;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.exception.OutOfStockException;
import com.example.JustBuy.model.*;
import com.example.JustBuy.repository.CardRepository;
import com.example.JustBuy.repository.CartRepository;
import com.example.JustBuy.repository.CustomerRepository;
import com.example.JustBuy.repository.OrderedRepository;
import com.example.JustBuy.service.CartService;
import com.example.JustBuy.service.OrderService;
import com.example.JustBuy.service.ProductService;
import com.example.JustBuy.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    ProductService productService;

    @Override
    public CartResponseDto saveCart(int customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal =  cart.getTotalCost() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setTotalCost(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());

        Cart savedcart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(cart.getTotalCost())
                .customerName(customer.getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for(Item itemEntity : cart.getItems()){

            ItemResponseDto itemResponseDto = ItemTransformer.itemToItemResponseDto(itemEntity);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;

    }
    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());

//        SimpleDateFormat sampleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        // check date and cardNO fo 16 digit

        if(card == null || card.getCvv() != checkOutCartRequestDto.getCvv() || card.getCustomer() != customer
                || card.getCardNo().length() < 16){
            throw new InvalidCardException("Your card is not valid!!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0){
            throw new InvalidCartException("Cart is empty!!!");
        }

        try {
            Ordered order = orderService.placeOrder(customer, card);
            customer.getOrderedList().add(order);
            Ordered savedOrder = orderedRepository.save(order);
            resetCart(cart);

//            customerRepository.save(customer);

            // prepare response DTO
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setUsedCard(savedOrder.getUsedCard());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalCost(savedOrder.getTotalCost());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item item : savedOrder.getItems()){
                ItemResponseDto itemResponseDto = ItemTransformer.itemToItemResponseDto(item);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItems(items);

            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void resetCart(Cart cart){
        cart.setTotalCost(0);
        cart.setNumberOfItems(0);
        cart.setItems(new ArrayList<>());
    }
}
