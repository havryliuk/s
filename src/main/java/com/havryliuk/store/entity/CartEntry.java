package com.havryliuk.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CartEntry {
    private Customer customer;
    private Product product;
    private int quantity;
}
