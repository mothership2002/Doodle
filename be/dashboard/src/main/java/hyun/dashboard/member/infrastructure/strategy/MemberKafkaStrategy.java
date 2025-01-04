package hyun.dashboard.member.infrastructure.strategy;

import hyun.dashboard.member.domain.Member;
import hyun.dashboard.member.dto.MemberReq;
import hyun.dashboard.member.infrastructure.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberKafkaStrategy implements MemberStrategy {

    private final MemberRepository memberRepository;

    @Override
    public Mono<Member> update(Long id, MemberReq req) {
        return Mono.just(new Member(id, req.getAccount(), req.getPassword()));
    }

    @Override
    public Mono<Member> deleteByIdCustom(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Mono<Member> create(Member member) {
        return Mono.just(member);
    }
}
