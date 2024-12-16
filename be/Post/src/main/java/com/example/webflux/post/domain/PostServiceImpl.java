package com.example.webflux.post.domain;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.post.domain.PostService;
import com.example.webflux.post.dto.PostReq;
import com.example.webflux.post.dto.PostResp;
import com.example.webflux.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public Flux<PostResp> findAll(int page, int size, OrderBy orderBy) {
        return null;
    }

    @Override
    public Mono<PostResp> create(PostReq req) {
        return null;
    }

    @Override
    public Mono<Void> delete(long id) {
        return null;
    }

    @Override
    public Mono<Long> update(long id, PostReq req) {
        return null;
    }
}
