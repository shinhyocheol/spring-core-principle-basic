package com.core.example.autowired;

import com.core.example.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * 자동, 수동의 올바른 실무 운영 기준
 *
 * "편리한 자동 기능을 기본으로 사용하자"
 *  스프링이 나오고 시간이 갈 수록 점점 자동을 선호하는 추세다. @Component 뿐만이 아니라
 *  '@Controller' , '@Service' 처럼 계층에 맞추어 일반적인 애플리케이션 로직을 자동으로 스캔할 수 있도록 지원한다.
 *  설정 정보를 기반으로 애플리케이션을 구성하는 부분과 실제 동작하는 부분을 명확하게 나누는 것이 이상적이지만, 개발자 입장에서
 *  스프링 빈을 하나 등록할 때 @Component 만 넣어주면 끝나는 일을 @Configuration 설정 정보에 가서 @Bean 을 적고,
 *  객체를 생성하고, 주입할 대상을 일일이 적어주는 과정은 상당히 번거롭다.
 *  또 관리할 빈이 많아서 설정 정보가 커지면 설정 정보를 관리하는 것 자체가 부담이 된다.
 *  그리고 결정적으로 자동 빈 등록을 사용해도 OCP, DIP 를 지킬 수 있다.
 *
 *  "그렇다면 수동 빈 등록은 언제 사용하면 좋을까?"
 *  애플리케이션은 크게 업무로직과 기술 지원 로직으로 나눌 수 있다.
 *      1. 업무로직 빈
 *          - 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리등이 모두 업무 로직이다.
 *      2. 기술지원 빈
 *          - 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용된다. 데이터베이스 연결이나, 공통 로그 처리 처럼 업무 로직을 지원하기 위한 하부 기술이나 공통 기술이다.
 *
 *  - 업무 로직은 숫자도 매우 많고, 한번 개발해야 하면 컨트롤러, 서비스, 리포지토리 처럼 어느정도 유사한 패턴이 있다. 이런 경우
 *    자동 기능을 적극 사용하는 것이 좋다. 보통 문제가 발생해도 어떤 곳에서 문제가 발생했는지 파악하기 쉽다.
 *
 *  - 기술 지원 로직은 업무 로직과 비교해서 그 수가 매우 적고, 보통 애플리케이션 전반에 걸쳐서 광범위하게 영향을 미친다. 그리고 업무 로직은
 *  문제가 발생했을 때 어디가 문제인지 명확하게 잘 들어나지만, 기술지원 로직은 적용이 잘 되고 있는지 아닌지 조차 파악하기 어려운 경우가 많다.
 *  그래서 이런 기술 지원 로직들은 가급적 수동 빈 등록을 사용해서 명확하게 드어내는 것이 좋다.
 *
 *  "애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록해서 딱! 설정 정보에 바로 나타나게 하는 것이 유지보수 하기 좋다"
 *
 *
 */
public class AutowiredTest {
    // 존재하지 않는 빈을 등록하는 경우 테스트
    @Test
    void AutowiredTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출이 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 자동 주입할 대상이 없으면 null이 입력된다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
