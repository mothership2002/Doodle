package hyun.dashboard.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResp {

    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String createdAt;
    private String updatedAt;

    // TODO temp
    private byte[] image;
}
