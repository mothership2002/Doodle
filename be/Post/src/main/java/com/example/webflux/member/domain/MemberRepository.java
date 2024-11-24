package com.example.webflux.member.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MemberRepository extends R2dbcRepository<Member, Long>, MemberRepositoryCustom {

    Mono<Member> findOneByAccount(String account);
}