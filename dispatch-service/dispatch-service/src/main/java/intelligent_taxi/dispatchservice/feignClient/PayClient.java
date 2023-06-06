package intelligent_taxi.dispatchservice.feignClient;

import intelligent_taxi.dispatchservice.dto.pay.CheckBalanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static intelligent_taxi.dispatchservice.feignClient.constant.PayUrl.*;

@FeignClient(name = BASE)
public interface PayClient {

    @PostMapping(CHECK_BALANCE)
    boolean checkBalance(@RequestBody CheckBalanceRequest requestDto);
}
