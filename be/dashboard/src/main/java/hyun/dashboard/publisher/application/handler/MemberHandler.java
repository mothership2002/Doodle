package hyun.dashboard.publisher.application.handler;

import hyun.dashboard.publisher.member.domain.MemberService;
import hyun.dashboard.publisher.member.dto.MemberReq;
import hyun.dashboard.publisher.member.dto.MemberResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Deprecated
//@Component
@RequiredArgsConstructor
public class MemberHandler {

//    private final MemberService memberService;
//
//    // TODO pagination
//    public Mono<ServerResponse> get(ServerRequest serverRequest) {
////        serverRequest.queryParams()
////        return
//        return ServerResponse.ok().headers(getHeader()).body(memberService.findAll(), MemberResp.class);
//    }
//
//    public Mono<ServerResponse> create(ServerRequest serverRequest) {
//        return serverRequest.bodyToMono(MemberReq.class)
//                .flatMap(memberService::create)
//                .flatMap(memberResp -> ServerResponse.ok()
//                        .headers(getHeader())
//                        .body(Mono.just(memberResp), MemberResp.class));
//    }
//
//    // TODO authorized;
//    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
//        return Mono.just(serverRequest.pathVariable("id"))
//                .map(Long::parseLong)
//                .flatMap(memberService::delete)
//                .then(ServerResponse.noContent().build());
//    }
//
//    // TODO authorized, exception handling
//    public Mono<ServerResponse> update(ServerRequest serverRequest) {
//        return serverRequest.bodyToMono(MemberReq.class)
//                .zipWith(Mono.just(Long.parseLong(serverRequest.pathVariable("id"))))
//                .flatMap(tuple -> memberService.update(tuple.getT2(), tuple.getT1()))
//                .then(ServerResponse.noContent().build());
//    }
//
//    private void validate() {
//
//    }
}
