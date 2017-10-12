package com.havryliuk.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.havryliuk.dao.CartDao;
import com.havryliuk.entity.CartEntry;
import com.havryliuk.entity.Customer;
import com.havryliuk.entity.Product;
import com.havryliuk.entity.ProductCategory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartServiceTest {
    @Test
    public void testAddProductToCart() {
        CartDao cartDao = mock(CartDao.class);
        when(cartDao.recordForProductAndCustomerExists(anyInt(), anyInt())).thenReturn(true);
        when(cartDao.update(any(CartEntry.class))).thenReturn(true);
        Map<String, Integer> cartEntry = ImmutableMap.of("customerId", 1, "productId", 1, "quantity", 0);
        when(cartDao.findByProductId(anyInt())).thenReturn(cartEntry);

        ProductService productService = mock(ProductService.class);
        Product product = Product.builder().id(1).description("test product").price(BigDecimal.valueOf(1.5))
                .category(ProductCategory.OTHER).build();
        when(productService.getProductById(anyInt())).thenReturn(Optional.ofNullable(product));

        CustomerService customerService = mock(CustomerService.class);
        Customer customer = Customer.builder().id(1).name("test customer").blocked(false).build();
        when(customerService.getCustomerById(anyInt())).thenReturn(Optional.ofNullable(customer));

        CartService cartService = CartService.getInstance();
        cartService.setProductService(productService);
        cartService.setCustomerService(customerService);
        cartService.setCartDao(cartDao);

        boolean result = cartService.addProductToCart(new CartEntry(customer, product, 5));
        assertThat(result).isTrue();
    }
}
