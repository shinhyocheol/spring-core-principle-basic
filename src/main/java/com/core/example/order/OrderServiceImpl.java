package com.core.example.order;

import com.core.example.discount.DiscountPolicy;
import com.core.example.member.Member;
import com.core.example.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 불변, 필수
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
    public OrderServiceImpl(MemberRepository memberRepository,
                            DiscountPolicy discountPolicy) {
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
