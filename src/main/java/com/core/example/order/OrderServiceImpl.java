package com.core.example.order;

import com.core.example.discount.DiscountPolicy;
import com.core.example.member.Member;
import com.core.example.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * [의존성 주입의 방식은 한가지가 아닌데 그 중 생성자 주입을 선택하는 이유]
 *  과거에는 수정자 주입과 필드 주입을 많이 사용했지만, 최근에는 스프링을 포함한 DI 프레임워크 대부분이 생성자 주입을 권장한다.
 *  이유는 아래와 같다.
 *
 *  "불변"
 *     - 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야한다)
 *     - 수정자 주입을 사용하면 setXxx 메서드를 public 으로 열어두어야 한다.
 *     - 누군가 실수로 변경할 수도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
 *     - 생성자 주입은 객체를 생성할 때 딱 1번 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다.
 * 
 *  정리
 *     - 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.
 *     - 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수장자 주입 방식을 옵션으로 부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
 *     - 항상 생성자 주입을 선택해라! 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 가급적 사용하지 않는 것이 좋다.
 */
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // [불변, 필수]
    // 생성자 주입을 사용하면 필드에 "final" 키워드를 사용할 수 있다. 그래서 생성자에게 혹시라도 값이 설정되지 않는 오류를
    // 컴파일 시점에서 확인할 수 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * @Autowired
     * 스프링은 런타임시 두가지를 나눠서 실행한다.
     * 1. Bean 을 찾아서 모두 컨테이너에 등록한다.
     * 2. 클래스 간의 의존성 관계를 설정한다. 여기서 의존 관계를 설정할 때 @Autowired 를 확인하여 각 클래스 별로 설정된 의존성 관계를 맺어준다.
     *
     * 또한 @Autowired 는 대상이 스프링 빈이어야 한다. 빈이 아닌 클래스에 해당 어노테이션을 적용해도 아무런 동작을 하지 않는다.
     */
    // 생성자가 하나인 경우 @Autowired 를 생략할 수 있다.
    // @Autowired
    // public OrderServiceImpl(MemberRepository memberRepository,
    //                        DiscountPolicy discountPolicy) {
    //    System.out.println("memberRepository = " + memberRepository);
    //    System.out.println("discountPolicy = " + discountPolicy);

    //    this.memberRepository = memberRepository;
    //    this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
