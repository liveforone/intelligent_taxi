package intelligent_taxi.dispatchservice.clientWrapper;

import intelligent_taxi.dispatchservice.feignClient.MemberClient;
import intelligent_taxi.dispatchservice.feignClient.constant.CircuitLog;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberClientWrapper {

    private final MemberClient memberClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public String getBankbookNum(String username) {
        return circuitBreakerFactory
                .create(CircuitLog.DISPATCH_CIRCUIT_LOG.getValue())
                .run(
                        () -> memberClient.getBankbookNum(username),
                        throwable -> null
                );
    }
}
