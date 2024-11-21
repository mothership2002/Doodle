package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@NoArgsConstructor
public class Member extends BaseEntityColumn {

    @Id
    private Long id;

    @Column(value = "ACCOUNT")
    private String account;

    @Column(value = "PASSWORD")
    private String password;

    // TODO additionally ROLE <<

    public Member(String account, String password) {
        super(LocalDateTime.now());
        this.account = account;
        this.password = password;
    }

    public Member(Long id, String account, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.account = account;
    }

    /**
     * update method
     * @param member given modified member info
     */
    public void update(Member member) {
        super.update(LocalDateTime.now());
        this.password = member.getPassword();
    }
}
