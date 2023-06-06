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

    //service 호출
    ObjectMapper objectMapper = new ObjectMapper();

    /*
    @KafkaListener(topics = Topic)
    @Async(AsyncConstant.commandAsync)
    public void increaseShopIsGood(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG.getValue() + kafkaMessage);

        Long shopId = objectMapper.readValue(kafkaMessage, Long.class);

        if (CommonUtils.isNull(shopId)) {
            log.info(KafkaLog.KAFKA_NULL_LOG.getValue());
        } else {
            log.info(KafkaLog.INCREASE_SHOP_GOOD.getValue() + shopId);
        }
    }
     */
}
