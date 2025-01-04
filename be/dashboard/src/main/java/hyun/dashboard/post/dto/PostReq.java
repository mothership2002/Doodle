package hyun.dashboard.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostReq {

    private Long id;
    private String title;
    private String content;
    private Long memberId;

    // TODO temp
    private byte[] image;
}
