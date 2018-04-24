package com.havryliuk.store.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductQuantity {
    private int productId;
    private int quantity;
}