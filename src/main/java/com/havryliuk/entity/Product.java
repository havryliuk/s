package com.havryliuk.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Product implements Serializable {
    private int id;
    private String description;
    private BigDecimal price;
    private ProductCategory category;
}
