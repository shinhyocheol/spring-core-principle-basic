package com.core.example;

import com.core.example.discount.DiscountPolicy;
import com.core.example.discount.RateDiscountPolicy;
import com.core.example.member.MemberService;
import com.core.example.member.MemberServiceImpl;
import com.core.example.member.MemoryMemberRepository;
import com.core.example.order.OrderService;
import com.core.example.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 컨테이너는 @Configuration 이 붙은 'AppConfig' 를 설정정보를 사용한다.
 * 어떻게?? 왜??
 *  -> ApplicationContext 를 AnnotationConfigApplicationContext 타입으로 생성하면서 파라미터로 AppConfig.class 를 등록해주기 때문이다.
 *
 *
 *  스프링 컨테이너 환경으로 변경하면서 코드가 약간 더 복잡해진 것 같은데, 스프링 컨테이너를 사용하면 어떤 장점이 있을까??
 */
@Configuration
public class AppConfig {

    /**
     * 스프링 빈은 '@Bean' 이 붙은 'AppConfig' 를 설정(구성) 정보로 사용한다.
     * 여기서 해당 어노테이션이 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
     * 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
     * 스프링 빈은 기본적으로 '@Bean' 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.
     *  ex) memberService() -> memberService, orderService() -> orderService
     * 물론 name 속성을 통해 이름을 별도로 지정해줄 수 있다.
     */

    // @Bean memberService --> new MemoryMemberRepository()
    // @Bean orderService --> new MemoryMemberRepository()
    /**
     * memberService 를 호출하면, memberRepository() 를 호출한다.
     * orderService 를 호출하면, memberRepository() 를 호출한다.
     *
     * 결과적으로 각각 다른 2개의 메서드에서 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것처럼 보일 수 있다.
     * 스프링 컨테이너는 이러한 문제를 해결하고, 결국 하나의 공유객체로 생성한다.
     */

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
