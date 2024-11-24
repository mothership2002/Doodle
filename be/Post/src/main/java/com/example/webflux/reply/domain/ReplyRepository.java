package com.example.webflux.reply.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReplyRepository extends R2dbcRepository<Reply, Long> {

}
