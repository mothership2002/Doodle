package hyun.dashboard.publisher.post.domain;

import hyun.dashboard.publisher.common.model.vo.OrderBy;
import hyun.dashboard.publisher.post.domain.PostService;
import hyun.dashboard.publisher.post.dto.PostReq;
import hyun.dashboard.publisher.post.dto.PostResp;
import hyun.dashboard.publisher.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public Flux<PostResp> findAll(int page, int size, OrderBy orderBy) {
        return null;
    }

    @Override
    public Mono<PostResp> create(PostReq req) {
        return null;
    }

    @Override
    public Mono<Void> delete(long id) {
        return null;
    }

    @Override
    public Mono<Long> update(long id, PostReq req) {
        return null;
    }
}
