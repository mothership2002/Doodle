package com.example.webflux.member.domain.service;

import com.example.webflux.event.SimpleEventPublisher;
import com.example.webflux.member.domain.model.Member;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.event.MemberCreateEvent;
import com.example.webflux.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final SimpleEventPublisher publisher;

    public Flux<MemberResp> findAll() {
        return memberRepository.findAll().map(MemberResp::new);
    }

    public Flux<MemberResp> findAll(int page, int size, OrderBy orderBy) {
        return memberRepository.findAllByPage(page, size, orderBy)
                .map(MemberResp::new);
    }

    @Transactional
    public Mono<MemberResp> create(MemberReq req) {
        return checkDuplicate(req.getAccount())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("Account already exists"));
                    } else {
                        Member member = new Member(req.getAccount(), req.getPassword());
                        publisher.publish(new MemberCreateEvent(this, member));
                        return memberRepository.save(member).map(MemberResp::new);
                    }
                });
    }

    private Mono<Boolean> checkDuplicate(String account) {
        return memberRepository.findOneByAccount(account)
                .hasElement();
    }

    public Mono<Void> delete(long id) {
        return memberRepository.deleteById(id);
    }

    @Transactional
    public Mono<Long> update(long id, MemberReq req) {
        return memberRepository.update(id, req.getAccount(), req.getUpdateMap());
    }
}
