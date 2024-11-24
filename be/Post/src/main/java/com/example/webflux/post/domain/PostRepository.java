package com.example.webflux.post.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<Post, Long>, PostRepositoryCustom {
}
