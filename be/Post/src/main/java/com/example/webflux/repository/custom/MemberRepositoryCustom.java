package com.example.webflux.repository.custom;

import com.example.webflux.model.entity.Member;
import com.example.webflux.model.vo.OrderBy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MemberRepositoryCustom {

    Flux<Member> findAllByPage(int page, int size, OrderBy orderBy);

    Mono<Long> update(Long id, String account, Map<String, String> updatedField);
}
