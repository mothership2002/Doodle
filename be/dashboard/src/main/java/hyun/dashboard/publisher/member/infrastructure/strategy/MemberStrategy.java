package hyun.dashboard.publisher.member.infrastructure.strategy;

import hyun.dashboard.publisher.member.domain.Member;
import hyun.dashboard.publisher.member.dto.MemberReq;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MemberStrategy {

    Mono<Member> update(Long id, MemberReq req);
    Mono<Member> deleteByIdCustom(Long id);
    Mono<Member> create(Member member);

}

