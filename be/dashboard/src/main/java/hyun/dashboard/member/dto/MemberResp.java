package hyun.dashboard.member.dto;

import hyun.dashboard.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResp {

    private Long memberId;
    private String account;
    private String createdAt;

    public MemberResp(Member member) {
        this.memberId = member.getId();
        this.account = member.getAccount();
        this.createdAt = member.getCreatedAt();
    }
}
