package com.havryliuk.store.service;

import org.junit.Test;

import com.havryliuk.store.dao.CartDao;
import com.havryliuk.store.entity.CartEntry;
import com.havryliuk.store.entity.Customer;
import com.havryliuk.store.entity.Product;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartServiceTest {
    @Test
    public void addProductToCartTest() {
        CartDao cartDao = mock(CartDao.class);
        when(cartDao.save(any(CartEntry.class))).thenReturn(1);
        Customer customer = Customer.builder().build();
        Product product = Product.builder().build();
        CartEntry cartEntry = new CartEntry(customer, product, 1);
        CartService service = new CartService(cartDao);
        boolean result = service.addProductToCart(cartEntry);
        assertTrue(result);
    }
}
