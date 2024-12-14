package com.example.webflux.member.infrastructure.strategy;

import com.example.webflux.member.domain.Member;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberKafkaStrategy implements MemberStrategy {

    private final MemberRepository memberRepository;

    @Override
    public Mono<Member> update(Long id, MemberReq req) {
        return Mono.just(new Member(id, req.getAccount(), req.getPassword()));
    }

    @Override
    public Mono<Member> deleteByIdCustom(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Mono<Member> create(Member member) {
        return Mono.just(member);
    }
}
