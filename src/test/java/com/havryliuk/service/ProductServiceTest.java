package com.havryliuk.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {
    @Test
    public void testProductServiceInstance() {
        ProductService service1 = ProductService.getInstance();
        ProductService service2 = ProductService.getInstance();
        assertThat(service1).isEqualTo(service2);
    }
}
