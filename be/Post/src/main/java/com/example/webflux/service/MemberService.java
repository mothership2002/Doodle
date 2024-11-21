package com.example.webflux.service;

import com.example.webflux.model.dto.MemberReq;
import com.example.webflux.model.dto.MemberResp;
import com.example.webflux.model.dto.MemberUpdateReq;
import com.example.webflux.model.entity.Member;
import com.example.webflux.model.vo.OrderBy;
import com.example.webflux.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

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
    public Mono<Long> update(long id, MemberUpdateReq req) {
        return memberRepository.update(id, req.getAccount(), req.getUpdateMap());
    }
}
