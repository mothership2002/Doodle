package hyun.dashboard.publisher.post.domain;

import hyun.dashboard.publisher.common.model.vo.OrderBy;
import hyun.dashboard.publisher.post.dto.PostReq;
import hyun.dashboard.publisher.post.dto.PostResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Flux<PostResp> findAll(int page, int size, OrderBy orderBy);

    Mono<PostResp> create(PostReq req);

    Mono<Void> delete(long id);

    Mono<Long> update(long id, PostReq req);
}
