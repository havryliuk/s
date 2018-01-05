package com.havryliuk.store.entity;

import java.math.BigDecimal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    public void testProductBuilder() {
        Product product = Product.builder().id(1).description("test product").price(BigDecimal.valueOf(1.23))
                .category(ProductCategory.OTHER).build();
        assertThat(product.toString()).isEqualTo("Product(id=1, description=test product, price=1.23, category=OTHER)");
    }
}
