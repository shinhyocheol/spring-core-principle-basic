package com.order.example;

import com.order.example.discount.DiscountPolicy;
import com.order.example.discount.FixDiscountPolicy;
import com.order.example.member.MemberService;
import com.order.example.member.MemberServiceImpl;
import com.order.example.member.MemoryMemberRepository;
import com.order.example.order.OrderService;
import com.order.example.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
