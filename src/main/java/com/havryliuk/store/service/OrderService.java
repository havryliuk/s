package com.havryliuk.store.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Order;
import com.havryliuk.store.entity.Product;
import com.havryliuk.store.dao.OrderDao;

public class OrderService {
    @Autowired
    private OrderDao orderDao;
    private static final Logger LOG = Logger.getLogger(OrderService.class);

    @Transactional
    public Optional<Order> createOrder(Customer customer, Map<Product, Integer> products) {
        Order order = Order.builder().products(products).customer(customer).paid(false).build();
        int id = orderDao.save(order);
        if (id > 0) {
            LOG.info("Created order: " + order);
            return Optional.ofNullable(order);
        }
        LOG.warn("Failed to create order: " + order);
        return Optional.empty();
    }

    public List<Order> getOrdersForCustomer(int customerId) {
        return orderDao.findAllByCustomerId(customerId);
    }

    public Order getOrderById(int id) {
        return orderDao.find(id);
    }

    public boolean payOrder(int id) {
        boolean result = orderDao.updateAsPaid(id);
        LOG.info("Order ID: " + id + " " + (result ? "paid" : "not paid"));
        return result;
    }
}
