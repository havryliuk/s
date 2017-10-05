package com.havryliuk.entity;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Order {
    private int id;
    private Map<Product, Integer> products;
    private Customer customer;
    private boolean paid = false;
}
