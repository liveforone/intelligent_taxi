package intelligent_taxi.dispatchservice.command;

import com.google.gson.Gson;
import intelligent_taxi.dispatchservice.async.AsyncConstant;
import intelligent_taxi.dispatchservice.kafka.KafkaLog;
import intelligent_taxi.dispatchservice.kafka.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DispatchProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    @Async(AsyncConstant.commandAsync)
    public void requestOrder(Long dispatchId) {
        String jsonOrder = gson.toJson(dispatchId);
        String topic = Topic.REQUEST_ORDER;

        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
    }
}
