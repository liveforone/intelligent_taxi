package intelligent_taxi.dispatchservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaLog {

    KAFKA_RECEIVE_LOG("Consumer receive Kafka Message -> "),
    KAFKA_SEND_LOG("Kafka send Success | Topic : "),
    KAFKA_NULL_LOG("!! Kafka Message is Null !!"),
    ORDER_FAIL_ROLLBACK_DISPATCH("Order Fail | Dispatch Id : "),
    CALCULATE_FAIL_ROLLBACK_DISPATCH("Calculate Fail | Dispatch Id : ");

    private final String value;
}
