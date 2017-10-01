package com.havryliuk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartEntry {
    private Customer customer;
    private Product product;
    private int quantity;
}
