package com.havryliuk.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Order;
import com.havryliuk.entity.Product;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.OrderDao;

public class OrderService {
    private static OrderService instance;
    private static final Logger LOG = Logger.getLogger(OrderService.class);

    private OrderService() {}

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public Optional<Order> createOrder(Customer customer, Map<Product, Integer> products) {
        Order order = Order.builder().products(products).customer(customer).paid(false).build();
        OrderDao dao = DaoFactory.getOrderDao();
        int id = dao.save(order);
        if (id > 0) {
            LOG.info("Created order: " + order);
            return Optional.ofNullable(order);
        }
        LOG.warn("Failed to create order: " + order);
        return Optional.empty();
    }

    public List<Order> getOrdersForCustomer(int customerId) {
        OrderDao dao = DaoFactory.getOrderDao();
        return dao.findAllByCustomerId(customerId);
    }

    public Optional<Order> getOrderById(int id) {
        OrderDao dao = DaoFactory.getOrderDao();
        return dao.find(id);
    }

    public boolean payOrder(int id) {
        OrderDao dao = DaoFactory.getOrderDao();
        boolean result = dao.payOrder(id);
        LOG.info("Order ID: " + id + " " + (result ? "paid" : "not paid"));
        return result;
    }
}
