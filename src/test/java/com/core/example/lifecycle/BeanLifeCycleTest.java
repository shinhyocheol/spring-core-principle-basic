package com.core.example.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 빈의 이벤트 라이프사이클
 *   스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
 *   - 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
 *   - 소멸전 콜백 : 빈이 소멸되기 전에 호출
 *
 *   [참고] : 객체의 생성과 초기화를 분리하자
 *   생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는 이렇게 생성된 값들을 활용해서
 *   외부 커넥션을 연결하는 등 무거운 동작을 수행한다.
 *   따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 하는 부분을 명확하게 나누는 것이
 *   유지보수 관점에서 좋다. 물론 초기화 적입이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 다 처리하는게 더 나을 수 있다.
 *
 *  스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.
 *  1. 인터페이스(InitializingBean, DisposableBean)
 *  2. 설정 정보에 초기화 메서드, 종료 메서드 지정
 *  3. @PostConstruct, @PreDestory 애노테이션 지원
 */
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        /**
         * @Bean(initMethod = "init", destroyMethod = "close") 을 통한 라이프사이클 관리의 장점
         *  - 메서드 이름을 자유롭게 지을 수 있다.
         *  - 스프링 빈이 스프링에 의존하지 않는다.
         *  - 코드가 아니라 설정정보를 사용하기때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
         *
         *  "종료 메서드 추론"
         *   - @Bean 의 'destroyMethod' 속성에는 특별한 기능이 있다.
         *   - 라이브러리는 대부분 'close', 'shutdown' 이라는 이름의 종료 메서드를 사용한다.
         *   - @Bean 의 'destroyMethod' 는 기본값이 '(inferred)' (추론) 으로 등록되어 있다.
         *   - 이 추론 기능은 'close', 'shutdown' 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.
         *   - 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
         *   - 추론 기능을 사용하지 싫으면 destroyMethod = "" 처럼 빈 공백을 지정하면 된다.
         *
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
