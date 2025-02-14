package hyun.dashboard.publisher.post.infrastructure;

import hyun.dashboard.publisher.post.domain.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<Post, Long>, PostRepositoryCustom {
}
