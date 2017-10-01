package com.havryliuk.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Product implements Serializable {
    private int id;
    private String description;
    private BigDecimal price;
    private ProductCategory category;

    public void setId(int id) {
        this.id = id;
    }
}
