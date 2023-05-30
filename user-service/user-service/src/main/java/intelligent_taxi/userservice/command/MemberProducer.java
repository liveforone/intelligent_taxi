package intelligent_taxi.userservice.command;

import com.google.gson.Gson;
import intelligent_taxi.userservice.async.AsyncConstant;
import intelligent_taxi.userservice.kafka.KafkaLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static intelligent_taxi.userservice.kafka.Topic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    @Async(AsyncConstant.commandAsync)
    public void removeTaxi(String username) {
        String jsonOrder = gson.toJson(username);
        String topic = REMOVE_TAXI;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
    }
}
