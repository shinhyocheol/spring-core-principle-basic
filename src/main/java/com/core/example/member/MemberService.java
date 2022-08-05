package com.core.example.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
