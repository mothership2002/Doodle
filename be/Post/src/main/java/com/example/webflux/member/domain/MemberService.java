package com.example.webflux.member.domain;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberService {

    Flux<MemberResp> findAll();

    Flux<MemberResp> findAll(int page, int size, OrderBy orderBy);

    Mono<MemberResp> create(MemberReq req);

    Mono<Void> delete(long id);

    Mono<Long> update(long id, MemberReq req);
}
