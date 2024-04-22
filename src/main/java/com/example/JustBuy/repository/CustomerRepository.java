package com.example.JustBuy.repository;

import com.example.JustBuy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  public Customer findByEmailId(String emailId);

}
