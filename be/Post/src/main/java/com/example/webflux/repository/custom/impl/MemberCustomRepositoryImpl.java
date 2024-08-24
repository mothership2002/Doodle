package com.example.webflux.repository.custom.impl;

import com.example.webflux.model.entity.Member;
import com.example.webflux.model.vo.OrderBy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.example.webflux.repository.custom.MemberCustomRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final DatabaseClient dbc;


    @Override
    public Flux<Member> findAllByPage(int page, int size, OrderBy orderBy) {
        return dbc.sql(
                        " SELECT * " +
                        " FROM MEMBER " +
                        " ORDER by :columnName " +
                        " LIMIT :limit OFFSET :offect"
                )
                .bind("columnName", orderBy.getColumnName())
                .bind("limit", size)
                .bind("offset", size * page)
                .map((row, metadata) ->
                        new Member(
                                row.get("ID", Long.class),
                                row.get("ACCOUNT", String.class),
                                row.get("CREATED_AT", LocalDateTime.class),
                                row.get("UPDATED_AT", LocalDateTime.class))
                ).all();
    }
}
