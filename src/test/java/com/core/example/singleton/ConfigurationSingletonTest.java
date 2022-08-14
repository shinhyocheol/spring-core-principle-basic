package com.core.example.singleton;

import com.core.example.AppConfig;
import com.core.example.member.MemberRepository;
import com.core.example.member.MemberService;
import com.core.example.member.MemberServiceImpl;
import com.core.example.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService --> memberRepository : " + memberRepository1);
        System.out.println("orderService --> memberRepository : " + memberRepository2);
        System.out.println("memberRepository : " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDepp() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // 순수한 클래스라면 com.core.example.AppConfig 와 같이 출력이 되어야 한다.
        // 그런데 예상과는 다르게 클래스명에 xxCGLIB 가 붙으면서 상당히 복잡해진 것을 확인할 수 있다.
        // 이것은 내가 만든 클래스가 아니라 스프링이 CGLIB 이라는 바이트 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은
        // 임의의 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.
        // 바로 이 임의의 클래스가 싱글톤이 되도록 보장해주는 것이다. (실제로는 CGLIB 의 내부 기술을 사용하기에 매우 복잡할 수 있다)
        // 따라서 Bean 이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 새로 생성해서 스프링 빈으로 등록하고
        // 반환하는 코드가 동적으로 만들어진다. 이로 인해 싱글톤이 보장되는 것이다.

        // 참고로 AppConfig@CGLIB 는 AppConfig 의 자식 타입이므로, AppConfig 타입으로 조회할 수 있다.
        // 근데 만약 @Configuration 어노테이션을 사용하지 않고, @Bean 을 붙여 빈을 등록하게 되면 내가 등록한 클래스가 빈으로 등록이 된다.
        // 그러나 이렇게 되면 CGLIB 을 통한 임의의 클래스를 등록하지 않고, 싱글톤 규칙 또한 지켜지지 않는다.
    }
}
