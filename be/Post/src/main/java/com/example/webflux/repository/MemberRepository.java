package com.example.webflux.repository;

import com.example.webflux.model.entity.Member;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import com.example.webflux.repository.custom.MemberCustomRepository;

public interface MemberRepository extends R2dbcRepository<Member, Long>, MemberCustomRepository {

    Mono<Member> findOneByAccount(String account);
}