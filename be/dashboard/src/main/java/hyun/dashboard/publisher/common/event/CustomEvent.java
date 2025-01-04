package hyun.dashboard.publisher.common.event;

import hyun.dashboard.publisher.common.model.entity.Domain;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 퍼블리셔에서 받은 이벤트를 라우팅하는 이벤트로 사용할 것
 */
@Getter
public abstract class CustomEvent extends ApplicationEvent {

    protected final Domain domain;

    public CustomEvent(Object source, Domain domain) {
        super(source);
        this.domain = domain;
    }
}
