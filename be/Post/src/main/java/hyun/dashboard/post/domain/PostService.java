package hyun.dashboard.post.domain;

import hyun.dashboard.common.model.vo.OrderBy;
import hyun.dashboard.post.dto.PostReq;
import hyun.dashboard.post.dto.PostResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Flux<PostResp> findAll(int page, int size, OrderBy orderBy);

    Mono<PostResp> create(PostReq req);

    Mono<Void> delete(long id);

    Mono<Long> update(long id, PostReq req);
}
