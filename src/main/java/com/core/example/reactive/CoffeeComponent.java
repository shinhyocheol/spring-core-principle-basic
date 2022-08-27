package com.core.example.reactive;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoffeeComponent implements CoffeeUseCase{
    private final CoffeeRepository coffeeRepository;
    Executor executor = Executors.newFixedThreadPool(10);


    @Override
    public int getPrice(String name) {
        log.info("동기 호출로 커피 가격 조회");
        return coffeeRepository.getPriceByName(name);
    }

    @Override
    public Future<Integer> getPriceAsync(String name) {
        log.info("비동기 호출 방식으로 커피 가격 조회");
        CompletableFuture<Integer> future = new CompletableFuture<>();

        new Thread(() -> {
            log.info("스레드에 커피 가격을 호출하는 작업 할당");
            Integer price = coffeeRepository.getPriceByName(name);
            future.complete(price);
        }).start();

        return future;
    }

    @Override
    public Future<Integer> getDiscountPriceAsync(Integer price) {
        return null;
    }
}
