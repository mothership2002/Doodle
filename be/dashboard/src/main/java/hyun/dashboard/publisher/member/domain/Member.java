package hyun.dashboard.publisher.member.domain;

import hyun.dashboard.publisher.common.model.entity.Domain;
import hyun.dashboard.publisher.common.model.entity.BaseEntityColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@Getter
@NoArgsConstructor
public class Member extends BaseEntityColumn implements Domain {

    @Id
    private Long id;

    @Column(value = "ACCOUNT")
    private String account;

    @Column(value = "PASSWORD")
    private String password;

    // TODO additionally ROLE <<

    public Member(Long id, String account) {
        this.id = id;
        this.account = account;
    }

    public Member(Long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

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
     *               jpa 가 아니라 안될 수 도 있을것 같음.
     */
    public void update(Member member) {
        super.update(LocalDateTime.now());
        this.password = member.getPassword();
    }
}
