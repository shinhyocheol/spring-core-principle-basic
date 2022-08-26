package com.core.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * @Autowired 를 사용하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입을 시켜준다.
     * 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다. 생성자에 파라미터가 많아도 다 찾아서 자동으로 주입해준다.
     */
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
 
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
