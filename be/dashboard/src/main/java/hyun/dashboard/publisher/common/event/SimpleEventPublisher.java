package hyun.dashboard.publisher.common.event;

import hyun.dashboard.publisher.common.event.crud.CreateEvent;
import hyun.dashboard.publisher.common.event.crud.DeleteEvent;
import hyun.dashboard.publisher.common.event.crud.FindEvent;
import hyun.dashboard.publisher.common.event.crud.UpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(CustomEvent event) {
        if (event instanceof FindEvent) {
            publish((FindEvent) event);
        }
        if (event instanceof CreateEvent) {
            publish((CreateEvent) event);
        }
        if (event instanceof UpdateEvent) {
            publish((UpdateEvent) event);
        }
        if (event instanceof DeleteEvent) {
            publish((DeleteEvent) event);
        }
    }

    public void publish(FindEvent event) {
        System.out.println(event.getDomain());
    }

    public void publish(CreateEvent event) {
        publisher.publishEvent(event);
    }

    public void publish(UpdateEvent event) {
        System.out.println(event.getDomain());
    }

    public void publish(DeleteEvent event) {
        System.out.println(event.getDomain());
    }
}

