package com.example.webflux.repository;

import com.example.webflux.model.entity.Reply;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReplyRepository extends R2dbcRepository<Reply, Long> {

}
