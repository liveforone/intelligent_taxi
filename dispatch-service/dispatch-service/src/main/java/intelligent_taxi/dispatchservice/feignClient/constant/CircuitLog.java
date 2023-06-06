package intelligent_taxi.dispatchservice.feignClient.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CircuitLog {

    TIMETABLE_CIRCUIT_LOG("dispatch-circuit");

    private final String value;
}
