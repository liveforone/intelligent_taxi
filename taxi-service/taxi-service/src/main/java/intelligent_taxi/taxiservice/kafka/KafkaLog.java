package intelligent_taxi.taxiservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaLog {

    KAFKA_RECEIVE_LOG("Consumer receive Kafka Message -> "),
    KAFKA_NULL_LOG("!! Kafka Message is Null !!"),
    REMOVE_TAXI_BELONG_MEMBER("Remove Taxi Belong Member | Username : ");

    private final String value;
}
