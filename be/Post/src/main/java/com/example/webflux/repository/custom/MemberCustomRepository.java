package com.example.webflux.repository.custom;

import com.example.webflux.model.entity.Member;
import com.example.webflux.model.vo.OrderBy;
import reactor.core.publisher.Flux;

public interface MemberCustomRepository {

    Flux<Member> findAllByPage(int page, int size, OrderBy orderBy);
}
