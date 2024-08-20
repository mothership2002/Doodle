package com.example.webflux.service;

import com.example.webflux.model.dto.MemberReq;
import com.example.webflux.model.dto.MemberResp;
import com.example.webflux.model.entity.Member;
import com.example.webflux.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Flux<MemberResp> findAll() {
        return memberRepository.findAll().map(MemberResp::new);
    }

    public Mono<MemberResp> create(MemberReq memberReq) {
        return checkDuplicate(memberReq.getAccount())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("Account already exists"));
                    } else {
                        Member member = new Member(memberReq.getAccount(), memberReq.getPassword());
                        return memberRepository.save(member).map(MemberResp::new);
                    }
                });
    }

    private Mono<Boolean> checkDuplicate(String account) {
        return memberRepository.findOneByAccount(account)
                .hasElement();
    }
}
