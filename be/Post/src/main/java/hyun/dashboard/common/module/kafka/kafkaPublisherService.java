package hyun.dashboard.common.module.kafka;

import hyun.dashboard.common.event.CustomEvent;
import hyun.dashboard.common.event.crud.CreateEvent;
import hyun.dashboard.common.event.crud.DeleteEvent;
import hyun.dashboard.common.event.crud.UpdateEvent;
import hyun.dashboard.common.model.entity.Domain;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 중복코드가 많은감이 없네 퍼블리싱에 무슨 작업이 더있어야할까?
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class kafkaPublisherService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    @EventListener
    public void sendEvent(CustomEvent event) {
        publishEvent(event);
    }

    private void publishEvent(CustomEvent event) {
        Domain domain = event.getDomain();
        CompletableFuture<SendResult<String, String>> send = kafkaTemplate.send(domain.getClass().getSimpleName(),
                event.getClass().getSuperclass().getSimpleName(),
                gson.toJson(domain));

    }

}
