package com.example.JustBuy.repository;

import com.example.JustBuy.model.Card;
import com.example.JustBuy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

      public Card findByCardNo(String cadNo);

}
