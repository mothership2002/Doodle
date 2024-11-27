package com.example.webflux.post.infrastructure;

import com.example.webflux.post.domain.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<Post, Long>, PostRepositoryCustom {
}
