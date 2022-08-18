package com.core.example;

import com.core.example.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


/**
 * @ComponentScan 스캔 대상
 *   - @Component : 컴포넌트 스캔에서 사용
 *   - @Controller : 스프링 MVC 컨트롤러에서 사용
 *   - @Service : 스프링 비즈니스 로직에서 사용
 *   - @Repository : 스프링 데이터 접근 계층에서 사용
 *   - @Configuration : 스프링 설정 정보에서 사용
 *
 * 참고 : 어노테이션에는 상속관계라는 것이 없다.
 *       그래서 이렇게 어노테이션이 특정 어노테이션을 들고 있는 것을 인식할 수 있는 것은
 *       자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능이다.
 *
 * 컴포넌트 스캔의 용도 뿐만 아니라 다음 어노테이션이 있으면 스프링은 부가 기능을 수행한다.
 * @Controller : 스프링 MVC 컨트롤러로 인식
 * @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 반환해준다.
 * @Configuration : 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
 * @Service : 특별한 처리를 하지는 않지만, 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
 */
@ComponentScan(
        // 아래와 같이 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        // basePackages = "com.core.example.member",
        // basePackageClasses = AutoAppConfig.class,

        // AutoAppConfig 는 수동으로 등록하는 클래스이고, 만약 여기서 제외하지 않으면 충돌이 발생한다.
        // 보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 변경해야 하는 상황이 발생할 수 있으니
        // 그런 상황을 피하기 위해 제외 대상으로 지정한 것이다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
@Configuration
public class AutoAppConfig {
    /*
        @Bean("memoryMemberRepository")
        public MemoryMemberRepository memoryMemberRepository() {
            return new MemoryMemberRepository();
        }
    */
}
