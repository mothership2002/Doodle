package hyun.dashboard.publisher.member.infrastructure.repository;

import hyun.dashboard.publisher.common.model.vo.OrderBy;
import hyun.dashboard.publisher.member.domain.Member;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MemberRepositoryCustom {

    Flux<Member> findAllByPage(int page, int size, OrderBy orderBy);

    Mono<Member> update(Long id, String account, Map<String, String> updateMap);

    Mono<Member> deleteByIdCustom(Long id);
}
