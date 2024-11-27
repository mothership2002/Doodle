package com.example.webflux.post.application;

import com.example.webflux.common.model.vo.OrderBy;
import com.example.webflux.post.domain.PostService;
import com.example.webflux.post.dto.PostResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    public Flux<PostResp> findAll(@RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size,
                                  @RequestParam(required = false, defaultValue = "id") String columnName,
                                  @RequestParam(required = false, defaultValue = "ASC") String order) {
        return postService.findAll(page, size, new OrderBy(columnName, order));
    }
}