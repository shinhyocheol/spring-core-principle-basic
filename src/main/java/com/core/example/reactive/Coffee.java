package com.core.example.reactive;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Coffee {
    private String name;
    private Integer price;
}
