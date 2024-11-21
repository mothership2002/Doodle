package com.example.webflux.service;

import com.example.webflux.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ApplicationEventPublisher publisher;
    private final PostRepository postRepository;


}
