package com.order.example;

import com.order.example.member.Grade;
import com.order.example.member.Member;
import com.order.example.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        /**
         * ApplicationContext 를 스프링 컨테이너라 한다.
         * 기존에는 개발자가 'AppConfig'를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이를 통해서 사용한다.
         * 이전에는 필요한 객체를 'AppConfig' 를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다.
         * 스프링 빈은 'applicationContext.getBean()을 통해서 찾을 수 있다.
         * 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고,
         * 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
