package intelligent_taxi.dispatchservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_taxi.dispatchservice.async.AsyncConstant;
import intelligent_taxi.dispatchservice.kafka.KafkaLog;
import intelligent_taxi.dispatchservice.kafka.Topic;
import intelligent_taxi.dispatchservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DispatchConsumer {

    private final DispatchCommandService dispatchCommandService;
    private final DispatchProducer dispatchProducer;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = Topic.ORDER_FAIL_ROLLBACK_DISPATCH)
    @Async(AsyncConstant.commandAsync)
    public void orderFailRollbackDispatch(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG.getValue() + kafkaMessage);

        Long dispatchId = objectMapper.readValue(kafkaMessage, Long.class);

        if (CommonUtils.isNull(dispatchId)) {
            log.info(KafkaLog.KAFKA_NULL_LOG.getValue());
        } else {
            dispatchCommandService.rollbackDispatch(dispatchId);
            dispatchProducer.orderFailRollbackCalculate(dispatchId);
            log.info(KafkaLog.ORDER_FAIL_ROLLBACK_DISPATCH.getValue() + dispatchId);
        }
    }
}
