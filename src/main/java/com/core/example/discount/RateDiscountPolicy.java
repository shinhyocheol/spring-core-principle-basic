package com.core.example.discount;

import com.core.example.member.Member;
import com.core.example.member.Grade;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_PERCENT = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) return price * DISCOUNT_PERCENT / 100;
        return 0;
    }
}
