package intelligent_taxi.userservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_taxi.userservice.async.AsyncConstant;
import intelligent_taxi.userservice.kafka.KafkaLog;
import intelligent_taxi.userservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static intelligent_taxi.userservice.kafka.Topic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberConsumer {

    private final MemberCommandService memberCommandService;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = INCREASE_REPORT)
    @Async(AsyncConstant.commandAsync)
    public void increaseReport(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG.getValue() + kafkaMessage);

        String username = objectMapper.readValue(kafkaMessage, String.class);

        if (CommonUtils.isNull(username)) {
            log.info(KafkaLog.KAFKA_NULL_LOG.getValue());
        } else {
            memberCommandService.increaseReport(username);
            log.info(KafkaLog.INCREASE_REPORT.getValue() + username);
        }
    }

    @KafkaListener(topics = CANCEL_BLOCK)
    @Async(AsyncConstant.commandAsync)
    public void cancelBlock(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG.getValue() + kafkaMessage);

        String username = objectMapper.readValue(kafkaMessage, String.class);

        if (CommonUtils.isNull(username)) {
            log.info(KafkaLog.KAFKA_NULL_LOG.getValue());
        } else {
            memberCommandService.cancelBlock(username);
            log.info(KafkaLog.INCREASE_REPORT.getValue() + username);
        }
    }
}
