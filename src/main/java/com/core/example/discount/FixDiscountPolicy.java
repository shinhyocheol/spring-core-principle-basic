package com.core.example.discount;

import com.core.example.member.Member;
import com.core.example.member.Grade;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_FIX_AMOUNT = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) return DISCOUNT_FIX_AMOUNT;
        return 0;
    }
}
