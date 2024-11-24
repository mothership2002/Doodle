package com.example.webflux.post.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ApplicationEventPublisher publisher;
    private final PostRepository postRepository;


}
