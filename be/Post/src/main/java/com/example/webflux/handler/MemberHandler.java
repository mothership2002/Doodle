package com.example.webflux.handler;

import com.example.webflux.model.dto.MemberReq;
import com.example.webflux.model.dto.MemberResp;
import com.example.webflux.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.example.webflux.module.HeaderUtils.getHeader;

@Component
@RequiredArgsConstructor
public class MemberHandler {

    private final MemberService memberService;

    public Mono<ServerResponse> getMembers(ServerRequest request) {
        return ServerResponse.ok().headers(getHeader()).body(memberService.findAll(), MemberResp.class);
    }

    public Mono<ServerResponse> createMember(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MemberReq.class)
                .flatMap(memberService::create)
                .flatMap(memberResp -> ServerResponse.ok()
                        .headers(getHeader())
                        .body(Mono.just(memberResp), MemberResp.class));
    }
}
