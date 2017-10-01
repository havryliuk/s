package com.havryliuk.entity;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Order {
    private int id;
    private Map<Product, Integer> products;
    private Customer customer;
    private boolean paid = false;

    public void setId(int id) {
        this.id = id;
    }
}
