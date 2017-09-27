package com.havryliuk.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Order {
    private List<Product> products;
    private Customer customer;
    private boolean paid = false;
}
