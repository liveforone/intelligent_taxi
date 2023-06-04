package intelligent_taxi.taxiservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_taxi.taxiservice.async.AsyncConstant;
import intelligent_taxi.taxiservice.kafka.KafkaLog;
import intelligent_taxi.taxiservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static intelligent_taxi.taxiservice.kafka.Topic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaxiConsumer {

    private final TaxiCommandService taxiCommandService;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = REMOVE_TAXI)
    @Async(AsyncConstant.commandAsync)
    public void removeTaxi(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG.getValue() + kafkaMessage);

        String username = objectMapper.readValue(kafkaMessage, String.class);

        if (CommonUtils.isNull(username)) {
            log.info(KafkaLog.KAFKA_NULL_LOG.getValue());
        } else {
            taxiCommandService.deleteOneByUsername(username);
            log.info(KafkaLog.REMOVE_TAXI_BELONG_MEMBER.getValue() + username);
        }
    }
}
