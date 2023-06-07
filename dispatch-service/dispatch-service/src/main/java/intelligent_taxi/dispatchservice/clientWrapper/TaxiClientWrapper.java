package intelligent_taxi.dispatchservice.clientWrapper;

import intelligent_taxi.dispatchservice.dto.taxi.TaxiResponse;
import intelligent_taxi.dispatchservice.feignClient.TaxiClient;
import intelligent_taxi.dispatchservice.feignClient.constant.CircuitLog;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaxiClientWrapper {
    private final TaxiClient taxiClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public TaxiResponse getTaxiInfoByUsername(String username) {
        return circuitBreakerFactory
                .create(CircuitLog.DISPATCH_CIRCUIT_LOG.getValue())
                .run(
                        () -> taxiClient.getTaxiInfoByUsername(username),
                        throwable -> new TaxiResponse()
                );
    }
}
