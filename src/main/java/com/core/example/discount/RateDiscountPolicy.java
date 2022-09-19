package com.core.example.discount;

import com.core.example.annotation.MainDiscountPolicy;
import com.core.example.member.Member;
import com.core.example.member.Grade;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@MainDiscountPolicy
/**
 * @Qualifier("mainDiscountPolicy")
 * @Qualifier : 의존성 주입 시에 이름을 직접 지정하고, 의존관계를 주입받는 곳에서 동일하게 해당 어노테이션을 붙여준다.
 * 다만 @Qualifier 는 @Qualifier 를 찾는 용도로만 사용하는 것이 명확하고 좋다.
 */
public class RateDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_PERCENT = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) return price * DISCOUNT_PERCENT / 100;
        return 0;
    }
}
