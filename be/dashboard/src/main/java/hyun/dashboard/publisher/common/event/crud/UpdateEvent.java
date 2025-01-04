package hyun.dashboard.publisher.common.event.crud;

import hyun.dashboard.publisher.common.event.CustomEvent;
import hyun.dashboard.publisher.common.model.entity.Domain;

public abstract class UpdateEvent extends CustomEvent {

    public UpdateEvent(Object source, Domain domain) {
        super(source, domain);
    }
}
