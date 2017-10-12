package com.havryliuk.service;

import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.havryliuk.dao.OrderDao;
import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Order;
import com.havryliuk.entity.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Test
    public void testCreateOrder() {
        Customer customer = Customer.builder().id(1).name("test customer").blocked(false).build();
        Product product = Product.builder().build();
        Map<Product, Integer> products = ImmutableMap.of(product, 5);

        OrderDao orderDao = mock(OrderDao.class);
        when(orderDao.save(any(Order.class))).thenReturn(1);

        Order expectedOrder = Order.builder().products(products).customer(customer).paid(false).build();
        OrderService service = OrderService.getInstance();
        service.setOrderDao(orderDao);
        Optional<Order> order = service.createOrder(customer, products);
        assertThat(order).isPresent();
        assertThat(order.get()).isEqualTo(expectedOrder);
    }

    @Test
    public void testOrderNotCreated() {
        Customer customer = mock(Customer.class);
        Product product = mock(Product.class);
        Map<Product, Integer> products = ImmutableMap.of(product, 5);

        OrderDao orderDao = mock(OrderDao.class);
        when(orderDao.save(any(Order.class))).thenReturn(0);

        OrderService service = OrderService.getInstance();
        service.setOrderDao(orderDao);
        Optional<Order> order = service.createOrder(customer, products);
        assertThat(order).isEmpty();
    }
}
