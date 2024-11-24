package com.example.webflux.member.infrastructure;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.domain.model.Member;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MemberRepositoryCustom {

    Flux<Member> findAllByPage(int page, int size, OrderBy orderBy);

    Mono<Long> update(Long id, String account, Map<String, String> updatedField);
}
