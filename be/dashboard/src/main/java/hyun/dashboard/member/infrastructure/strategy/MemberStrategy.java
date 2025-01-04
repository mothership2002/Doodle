package hyun.dashboard.member.infrastructure.strategy;

import hyun.dashboard.member.domain.Member;
import hyun.dashboard.member.dto.MemberReq;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MemberStrategy {

    Mono<Member> update(Long id, MemberReq req);
    Mono<Member> deleteByIdCustom(Long id);
    Mono<Member> create(Member member);

}

