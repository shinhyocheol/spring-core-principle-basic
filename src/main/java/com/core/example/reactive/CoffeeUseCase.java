package com.core.example.reactive;

import java.util.concurrent.Future;

public interface CoffeeUseCase {
    int getPrice(String name);
    Future<Integer> getPriceAsync(String name);
    Future<Integer> getPriceAsyncNonBlockingAndNoReturn(String name);
    Future<Integer> getPriceAsyncNonBlockingAndReturn(String name);
    Future<Integer> getDiscountPriceAsync(Integer price);
}
