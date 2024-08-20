package com.example.webflux.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberReq {

    private String account;
    private String password;
}
