package com.havryliuk.store.entity;

import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Order {
    private int id;
    private Map<Product, Integer> products;
    private Customer customer;
    @Builder.Default
    private boolean paid = false;
}
