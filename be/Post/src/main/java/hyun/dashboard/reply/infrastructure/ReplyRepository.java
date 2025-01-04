package hyun.dashboard.reply.infrastructure;

import hyun.dashboard.reply.domain.Reply;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReplyRepository extends R2dbcRepository<Reply, Long> {

}
