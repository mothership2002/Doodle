package com.example.webflux.post.domain;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.post.dto.PostReq;
import com.example.webflux.post.dto.PostResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Flux<PostResp> findAll(int page, int size, OrderBy orderBy);

    Mono<PostResp> create(PostReq req);

    Mono<Void> delete(long id);

    Mono<Long> update(long id, PostReq req);
}
