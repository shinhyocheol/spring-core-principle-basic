package com.core.example.reactive;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CoffeeTest {

    @DisplayName("커피 가격을 동기 방식으로 조회한다.")
    @Test
    void getSyncCoffeePrice() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestCoffeeConfig.class);
        CoffeeComponent coffeeComponent = ac.getBean("coffeeComponent", CoffeeComponent.class);

        int price = 1100;
        int resultPrice = coffeeComponent.getPrice("latte");

        assertThat(price).isEqualTo(resultPrice);
    }

    @DisplayName("커피 가격을 비동기 방식으로 조회한다.")
    @Test
    void getAsyncCoffeePrice() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestCoffeeConfig.class);
        CoffeeComponent coffeeComponent = ac.getBean("coffeeComponent", CoffeeComponent.class);

        int price = 900;
        CompletableFuture<Integer> future = coffeeComponent.getPriceAsync("americano");
        log.info("아메리카노 가격 요청했는데, 아직 수행이 안되었으므로 기다리는 중 이지만, 다른 작업 수행 가능");
        Integer resultPrice = future.join();
        log.info("최종 가격을 응답 받음.");

        assertThat(price).isEqualTo(resultPrice);
    }

    @Configuration
    static class TestCoffeeConfig {
        @Bean
        public CoffeeRepository coffeeRepository() {
            return new CoffeeRepository();
        }
        @Bean
        public CoffeeComponent coffeeComponent() {
            return new CoffeeComponent(coffeeRepository());
        }
    }

}
