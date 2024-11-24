package com.example.webflux.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberReq {

    private String account;
    private String password;
}
