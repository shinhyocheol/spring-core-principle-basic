package com.core.example.reactive;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Coffee {
    private String name;
    private Integer price;
}
