package com.example.webflux.application.controller;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.domain.service.MemberService;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Flux<MemberResp> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size,
                                   @RequestParam(required = false, defaultValue = "id") String columnName,
                                   @RequestParam(required = false, defaultValue = "ASC") String order) {

        if (!"ASC".equalsIgnoreCase(order) && !"DESC".equalsIgnoreCase(order)) {
            // custom Exception?
            throw new IllegalArgumentException("Order must be ASC or DESC");
        }
        return memberService.findAll(page, size, new OrderBy(columnName, order));
    }

    @PostMapping
    public Mono<MemberResp> create(@RequestBody @Validated MemberReq req) {
        return memberService.create(req);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable long id) {
        return memberService.delete(id);
    }

    // TODO patch or put? 흠..
    @PutMapping("/{id}")
    public Mono<Long> update(@PathVariable long id, @RequestBody MemberReq req) {
        return memberService.update(id, req);
    }

}
