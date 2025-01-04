package hyun.dashboard.publisher.reply.infrastructure;

import hyun.dashboard.publisher.reply.domain.Reply;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReplyRepository extends R2dbcRepository<Reply, Long> {

}
