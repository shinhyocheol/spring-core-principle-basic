package com.core.example.reactive;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Repository;

public class CoffeeTest {

    @Getter
    @Builder
    public static class Coffee {
        private final String name;
        private final Integer price;
    }

    @Repository
    public interface CoffeeRepository {

    }
}
