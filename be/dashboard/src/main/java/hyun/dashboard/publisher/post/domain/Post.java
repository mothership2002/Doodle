package hyun.dashboard.publisher.post.domain;

import hyun.dashboard.publisher.common.model.entity.Domain;
import hyun.dashboard.publisher.common.model.entity.BaseUserEntityColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@NoArgsConstructor
public class Post extends BaseUserEntityColumn implements Domain {

    @Id
    private Long id;

    @Column(value = "TITLE")
    private String title;

    @Column(value = "CONTENT")
    private String content;

    // TODO optimized to select with child (Reply)

    public Post(String title, String content, Long memberId) {
        super(LocalDateTime.now(), memberId);
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content, Long memberId) {
        super.update(memberId);
        this.title = title;
        this.content = content;
    }
}
