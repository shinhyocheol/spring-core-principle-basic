package com.order.example;

import com.order.example.member.Grade;
import com.order.example.member.Member;
import com.order.example.member.MemberService;
import com.order.example.member.MemberServiceImpl;
import com.order.example.order.Order;
import com.order.example.order.OrderService;
import com.order.example.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
