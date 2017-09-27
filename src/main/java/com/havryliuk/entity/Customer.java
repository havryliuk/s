package com.havryliuk.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Customer {
    private int id;
    private String name;
    private boolean blocked = false;
}
