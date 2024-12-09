package com.example.webflux.member.domain;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import com.example.webflux.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @EventPublishPoint(type = EntityEvent.Type.FIND, domain = Member.class)
    public Flux<MemberResp> findAll() {
        return memberRepository.findAll().map(MemberResp::new);
    }

    @Override
    @EventPublishPoint(type = EntityEvent.Type.FIND, domain = Member.class)
    public Flux<MemberResp> findAll(int page, int size, OrderBy orderBy) {
        return memberRepository.findAllByPage(page, size, orderBy)
                .map(MemberResp::new);
    }

    @Override
    @Transactional
    @EventPublishPoint(type = EntityEvent.Type.CREATE, domain = Member.class)
    public Mono<MemberResp> create(MemberReq req) {
        return checkDuplicate(req.getAccount())
                .flatMap(exists -> {
                    if (exists) {
                        // TODO CustomException
                        return Mono.error(new IllegalArgumentException("Account already exists"));
                    } else {
                        Member member = new Member(req.getAccount(), req.getPassword());
                        return memberRepository.save(member).map(MemberResp::new);
                    }
                });
    }

    @Override
    @EventPublishPoint(type = EntityEvent.Type.DELETE, domain = Member.class)
    public Mono<Void> delete(long id) {
        return memberRepository.deleteById(id);
    }

    @Override
    @Transactional
    @EventPublishPoint(type = EntityEvent.Type.UPDATE, domain = Member.class)
    public Mono<Long> update(long id, MemberReq req) {
        return memberRepository.update(id, req.getAccount(), req.getUpdateMap());
    }

    // -------------------------------------------- private -------------------------------------------- //
    private Mono<Boolean> checkDuplicate(String account) {
        return memberRepository.findOneByAccount(account)
                .hasElement();
    }
}
