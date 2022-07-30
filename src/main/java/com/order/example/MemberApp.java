package com.order.example;

import com.order.example.member.Grade;
import com.order.example.member.Member;
import com.order.example.member.MemberService;
import com.order.example.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
