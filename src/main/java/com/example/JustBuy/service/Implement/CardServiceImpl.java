package com.example.JustBuy.service.Implement;

import com.example.JustBuy.dto.RequestDto.CardRequestDto;
import com.example.JustBuy.dto.ResponseDto.CardResponseDto;
import com.example.JustBuy.dto.ResponseDto.CustomerResponseDto;
import com.example.JustBuy.exception.InvalidCustomerException;
import com.example.JustBuy.model.Card;
import com.example.JustBuy.model.Customer;
import com.example.JustBuy.repository.CustomerRepository;
import com.example.JustBuy.service.CardService;
import com.example.JustBuy.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();

        if(customer == null){
            throw new InvalidCustomerException("Sorry! Customer doesn't exists");
        }

        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        return CardTransformer.cardToCardResponseDto(card);
    }
}

// TODO
// Check card expiry