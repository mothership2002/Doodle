package com.example.webflux.repository;

import com.example.webflux.model.entity.Post;
import com.example.webflux.repository.custom.PostRepositoryCustom;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<Post, Long>, PostRepositoryCustom {
}
