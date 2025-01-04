package hyun.dashboard.publisher.member.infrastructure.strategy;

import hyun.dashboard.publisher.member.domain.Member;
import hyun.dashboard.publisher.member.dto.MemberReq;
import hyun.dashboard.publisher.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MemberDatabaseStrategy implements MemberStrategy {

    private final MemberRepository memberRepository;

    @Override
    public Mono<Member> update(Long id, MemberReq req) {
        return memberRepository.update(id, req.getAccount(), req.getUpdateMap());
    }

    @Override
    public Mono<Member> deleteByIdCustom(Long id) {
        return memberRepository.deleteByIdCustom(id);
    }

    @Override
    public Mono<Member> create(Member member) {
        return memberRepository.save(member);
    }
}
