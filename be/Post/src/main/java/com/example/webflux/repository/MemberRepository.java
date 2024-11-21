package com.example.webflux.repository;

import com.example.webflux.model.entity.Member;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import com.example.webflux.repository.custom.MemberRepositoryCustom;

@Repository
public interface MemberRepository extends R2dbcRepository<Member, Long>, MemberRepositoryCustom {

    Mono<Member> findOneByAccount(String account);
}