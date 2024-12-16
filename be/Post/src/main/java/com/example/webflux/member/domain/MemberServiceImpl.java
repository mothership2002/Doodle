package com.example.webflux.member.domain;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import com.example.webflux.member.infrastructure.repository.MemberRepository;
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
    private final MemberServiceDelegate proxy;

    @Override
//    @EventPublishPoint(type = EntityEvent.Type.FIND, domain = Member.class)
    public Flux<MemberResp> findAll() {
        return memberRepository.findAll().map(MemberResp::new);
    }

    @Override
//    @EventPublishPoint(type = EntityEvent.Type.FIND, domain = Member.class)
    public Flux<MemberResp> findAll(int page, int size, OrderBy orderBy) {
        return memberRepository.findAllByPage(page, size, orderBy)
                .map(MemberResp::new);
    }

    @Override
    @Transactional
    public Mono<MemberResp> create(MemberReq req) {
        return checkDuplicate(req.getAccount())
                .flatMap(exists -> getMemberRespMono(req, exists));
    }

    private Mono<MemberResp> getMemberRespMono(MemberReq req, Boolean exists) {
        if (exists) {
            // TODO CustomException
            return Mono.error(new IllegalArgumentException("Account already exists"));
        } else {
            return proxy.create(new Member(req.getAccount(), req.getPassword())).map(MemberResp::new);
        }
    }

    @Override
    public Mono<Void> delete(long id) {
        return proxy.delete(Long.valueOf(id)).then();
    }

    @Override
    @Transactional
    public Mono<Long> update(long id, MemberReq req) {
        return proxy.update(id, req).map(Member::getId);
    }

    // -------------------------------------------- private -------------------------------------------- //
    private Mono<Boolean> checkDuplicate(String account) {
        return memberRepository.findOneByAccount(account)
                .hasElement();
    }


}