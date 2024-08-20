package com.example.webflux.repository;

import com.example.webflux.model.entity.Member;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface MemberRepository extends R2dbcRepository<Member, Long> {

    Mono<Member> findOneByAccount(String account);
}
