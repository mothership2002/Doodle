package hyun.dashboard.common.event.crud;

import hyun.dashboard.common.event.CustomEvent;
import hyun.dashboard.common.model.entity.Domain;

public abstract class CreateEvent extends CustomEvent {

    public CreateEvent(Object source, Domain domain) {
        super(source, domain);
    }
}
