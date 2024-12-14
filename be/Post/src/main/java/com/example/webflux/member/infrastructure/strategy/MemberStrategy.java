package com.example.webflux.member.infrastructure.strategy;

import com.example.webflux.member.domain.Member;
import com.example.webflux.member.dto.MemberReq;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MemberStrategy {

    Mono<Member> update(Long id, MemberReq req);
    Mono<Member> deleteByIdCustom(Long id);
    Mono<Member> create(Member member);

}

