package intelligent_taxi.dispatchservice.clientWrapper;

import intelligent_taxi.dispatchservice.dto.pay.CheckBalanceRequest;
import intelligent_taxi.dispatchservice.feignClient.PayClient;
import intelligent_taxi.dispatchservice.feignClient.constant.CircuitLog;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayClientWrapper {

    private final PayClient payClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public boolean checkBalance(String bankbookNum, long price) {
        CheckBalanceRequest requestDto = new CheckBalanceRequest();
        requestDto.setBankbookNum(bankbookNum);
        requestDto.setPrice(price);
        return circuitBreakerFactory
                .create(CircuitLog.DISPATCH_CIRCUIT_LOG.getValue())
                .run(
                        () -> payClient.checkBalance(requestDto),
                        throwable -> false
                );
    }
}
