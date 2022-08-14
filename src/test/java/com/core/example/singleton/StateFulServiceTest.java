package com.core.example.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    void stateFulSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        // ThreadA: A 사용자 10000원 주문
        stateFulService1.order("userA", 10000);
        // ThreadA: B 사용자 20000원 주문
        stateFulService2.order("userB", 20000);

        // ThreadA : 사용자 A가 주문금액 조회
        int price = stateFulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(stateFulService1.getPrice()).isEqualTo(20000);

        /**
         * * 최대한 단순히 설명하기 위해, 실제로 쓰레드는 사용하지 않았다.
         * * ThreadA가 사용자 A 코드를 호출하고, ThreadB가 사용자 B 코드를 호출했다고 가정하자.
         * * StateFulService 의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
         * * 사용자 A의 주문금액은 10000원이 되어야 하는데 20000원이라는 결과가 나왔다.
         * * 실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 문제들이 터진다.
         * * 진짜 공유필드는 조심해야한다. 스프링 빈은 항상 무상태(stateless)로 설계해야 한다.
         */
    }

    static class TestConfig {
        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}