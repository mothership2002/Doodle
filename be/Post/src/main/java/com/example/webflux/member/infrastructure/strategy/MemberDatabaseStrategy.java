package com.example.webflux.member.infrastructure.strategy;

import com.example.webflux.member.domain.Member;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MemberDatabaseStrategy implements MemberStrategy {

    private final MemberRepository memberRepository;

    @Override
    public Mono<Member> update(Long id, MemberReq req) {
        return memberRepository.update(id, req.getAccount(), req.getUpdateMap());
    }

    @Override
    public Mono<Member> deleteByIdCustom(Long id) {
        return memberRepository.deleteByIdCustom(id);
    }

    @Override
    public Mono<Member> create(Member member) {
        return memberRepository.save(member);
    }
}
