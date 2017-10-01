package com.havryliuk.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Order;
import com.havryliuk.entity.Product;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.OrderDao;

public class OrderService {
    public Optional<Order> createOrder(Customer customer, Map<Product, Integer> products) {
        Order order = Order.builder().products(products).customer(customer).paid(false).build();
        OrderDao dao = new DaoFactory().getOrderDao();
        int id = dao.save(order);
        if (id > 0) {
            return Optional.ofNullable(order);
        }
        return Optional.empty();
    }

    public List<Order> getOrdersForCustomer(int customerId) {
        OrderDao dao = new DaoFactory().getOrderDao();
        return dao.findAllByCustomerId(customerId);
    }

    public Optional<Order> getOrderById(int id) {
        OrderDao dao = new DaoFactory().getOrderDao();
        return dao.find(id);
    }

    public boolean payOrder(int id) {
        OrderDao dao = new DaoFactory().getOrderDao();
        return dao.payOrder(id);
    }
}
