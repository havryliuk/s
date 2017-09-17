package com.havryliuk.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product implements Serializable {
    private int id;
    private String description;
    private BigDecimal price;
    private ProductCategory category;
}
