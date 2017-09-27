package com.havryliuk.service;

import java.util.List;

import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Order;
import com.havryliuk.entity.Product;

public class OrderService {
    public Order createOrder(Customer customer, List<Product> products) {
        return Order.builder().products(products).customer(customer).paid(false).build();
    }
}
