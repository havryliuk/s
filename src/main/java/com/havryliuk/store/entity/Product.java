package com.havryliuk.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Product implements Serializable {
    private int id;
    private String description;
    private BigDecimal price;
    private ProductCategory category;
}
