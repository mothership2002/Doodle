package com.example.webflux.service;

import com.example.webflux.member.domain.MemberService;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void findAll_test() {
        Flux<MemberResp> all = memberService.findAll();
        StepVerifier.FirstStep<List<MemberResp>> listFirstStep = StepVerifier.create(all.collectList());
        listFirstStep.expectNextMatches(list -> {
            assertThat(list).hasSize(1);
            MemberResp memberResp = list.stream().findFirst().get();
            Assertions.assertThat(memberResp.getAccount()).isEqualTo("hello");
            return true;
        }).verifyComplete();
    }

    @Test
    void update_test() {
        Mono<Long> update = memberService.update(1L, new MemberReq(1L, "account", "world"));
        StepVerifier.create(update)
                .expectNextMatches(count -> count == 1L)
                .verifyComplete();


        Flux<MemberResp> all = memberService.findAll();
        StepVerifier.create(all.count())
                .assertNext(count -> assertThat(count).isEqualTo(1))
                .verifyComplete();

        StepVerifier.create(all)
                .expectNextMatches(
                        memberResp -> {
                            Assertions.assertThat(memberResp.getAccount())
                                    .isEqualTo("account");
                            return true;
                        }).verifyComplete();
    }
}