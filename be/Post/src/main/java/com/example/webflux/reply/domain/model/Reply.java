package com.example.webflux.reply.domain.model;

import com.example.webflux.common.model.entity.BaseUserEntityColumn;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
public class Reply extends BaseUserEntityColumn {

    @Id
    private Long id;

    @Column(value = "CONTENT")
    private String content;

    @Column(value = "PARENT_RELAY_ID")
    private Long parentRelayId;

    /**
     * single relay content
     * @param content relay content
     * @param createdBy create by
     */
    public Reply(String content, Long createdBy) {
        super(LocalDateTime.now(), createdBy);
        this.content = content;
    }

    /**
     * dependent relay content;
     * @param content reply content
     * @param createdBy create by
     * @param parentRelayId parent relay id
     */
    public Reply(String content, Long createdBy, Long parentRelayId) {
        super(LocalDateTime.now(), createdBy);
        this.content = content;
        this.parentRelayId = parentRelayId;
    }
}
