package hyun.dashboard.publisher.member.infrastructure.repository;

import hyun.dashboard.publisher.common.model.vo.OrderBy;
import hyun.dashboard.publisher.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final DatabaseClient dbc;
    private final R2dbcEntityTemplate template;

    @Override
    public Flux<Member> findAllByPage(int page, int size, OrderBy orderBy) {
        return query(
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

    @Override
    public Mono<Member> update(Long id, String account, Map<String, String> updatedField) {
        String collect = updatedField.entrySet().stream()
                .map(e ->
                        e.getKey() + " = '" + e.getValue() + "'")
                .collect(Collectors.joining(", "));
        return query(
                " UPDATE MEMBER m" +
                " SET " + collect +
                " WHERE m.id = :id" +
                " RETURNING *"
        )
                .bind("id", id)
                .map((row, rowMetadata) ->
                        new Member(row.get("id", Long.class), row.get("account", String.class))
                )
                .one();
    }

    @Override
    public Mono<Member> deleteByIdCustom(Long id) {
        // 삭제 후 반환 쿼리
        return query(
                " DELETE FROM member " +
                " WHERE id = :id " +
                " RETURNING *")
                .bind("id", id)
                .map((row, rowMetadata) ->
                        new Member(row.get("id", Long.class), row.get("account", String.class))
                )
                .one();
    }

    private DatabaseClient.GenericExecuteSpec query(String query) {
        return dbc.sql(query);
    }
}
