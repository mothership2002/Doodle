package com.example.webflux.application.controller;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.member.domain.MemberService;
import com.example.webflux.member.dto.MemberReq;
import com.example.webflux.member.dto.MemberResp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Flux<MemberResp> getAll(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size,
                                   @RequestParam(required = false, defaultValue = "id") String columnName,
                                   @RequestParam(required = false, defaultValue = "ASC") String order) {
        if (!"ASC".equalsIgnoreCase(order) && !"DESC".equalsIgnoreCase(order)) {
            throw new IllegalArgumentException("order must be ASC or DESC");
        }
        return memberService.findAll(page, size, new OrderBy(columnName, order));
    }

    @PostMapping
    public Mono<MemberResp> create(@Valid MemberReq req) {
        return memberService.create(req);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable long id) {
        return memberService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<Long> update(@PathVariable long id, @RequestBody MemberReq req) {
        return memberService.update(id, req);
    }

}
