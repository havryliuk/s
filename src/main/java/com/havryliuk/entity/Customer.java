package com.havryliuk.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Customer {
    private int id;
    private String name;
    @Builder.Default
    private boolean blocked = false;
}
