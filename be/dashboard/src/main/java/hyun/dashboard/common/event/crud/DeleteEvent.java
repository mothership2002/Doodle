package hyun.dashboard.common.event.crud;

import hyun.dashboard.common.event.CustomEvent;
import hyun.dashboard.common.model.entity.Domain;

public abstract class DeleteEvent extends CustomEvent {

    public DeleteEvent(Object source, Domain domain) {
        super(source, domain);
    }
}
