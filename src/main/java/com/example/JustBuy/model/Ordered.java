package com.example.JustBuy.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordered")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Ordered {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @CreationTimestamp
    Date orderDate;
    int totalCost;
    String orderNo;
    String usedCard;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;
}
