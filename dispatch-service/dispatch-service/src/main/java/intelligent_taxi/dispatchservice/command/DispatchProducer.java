package intelligent_taxi.dispatchservice.command;

import com.google.gson.Gson;
import intelligent_taxi.dispatchservice.async.AsyncConstant;
import intelligent_taxi.dispatchservice.dto.calculate.RequestCalculate;
import intelligent_taxi.dispatchservice.dto.order.RequestOrder;
import intelligent_taxi.dispatchservice.kafka.KafkaLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static intelligent_taxi.dispatchservice.kafka.Topic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class DispatchProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    @Async(AsyncConstant.commandAsync)
    public void requestOrder(Long dispatchId, Long taxiId, long price) {
        RequestOrder requestOrder = RequestOrder.builder()
                .dispatchId(dispatchId)
                .taxiId(taxiId)
                .price(price)
                .build();
        String jsonOrder = gson.toJson(requestOrder);
        String topic = REQUEST_ORDER;

        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void requestCalculate(Long dispatchId, Long taxiId, double distance, long price) {
        RequestCalculate requestCalculate = RequestCalculate.builder()
                .dispatchId(dispatchId)
                .taxiId(taxiId)
                .distance(distance)
                .price(price)
                .build();
        String jsonOrder = gson.toJson(requestCalculate);
        String topic = REQUEST_CALCULATE;

        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void orderFailRollbackCalculate(Long dispatchId) {
        String jsonOrder = gson.toJson(dispatchId);
        String topic = ORDER_FAIL_ROLLBACK_CALCULATE;

        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void calculateFailRollbackOrder(Long dispatchId) {
        String jsonOrder = gson.toJson(dispatchId);
        String topic = CALCULATE_FAIL_ROLLBACK_ORDER;

        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
    }
}
