package com.example.webflux.member.event;

import com.example.webflux.event.CustomEvent;
import com.example.webflux.member.domain.model.Member;
import lombok.Getter;


@Getter
public class MemberCreateEvent extends CustomEvent {

    private final Member member;

    public MemberCreateEvent(Object source, Member member) {
        super(source);
        this.member = member;
    }

}
