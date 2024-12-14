package com.example.webflux.member.domain;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.property.KafkaMonit;
import com.example.webflux.member.dto.MemberReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

// TODO I will not to use Database for write
// use only event published -> use kafka -> 장애 대응?

@Component
@RequiredArgsConstructor
public class MemberServiceDelegate {

    private final KafkaMonit kafkaMonit;

    @EventPublishPoint(type = EntityEvent.Type.UPDATE, domain = Member.class)
    protected Mono<Member> update(long id, MemberReq req) {
        return kafkaMonit.getMemberStrategy().update(id, req);
    }

    @EventPublishPoint(type = EntityEvent.Type.DELETE, domain = Member.class)
    protected Mono<Member> delete(Long id) {
        return kafkaMonit.getMemberStrategy().deleteByIdCustom(id);
    }

    @EventPublishPoint(type = EntityEvent.Type.CREATE, domain = Member.class)
    protected Mono<Member> create(Member member) {
        return kafkaMonit.getMemberStrategy().create(member);
    }
}
