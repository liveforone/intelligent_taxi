package intelligent_taxi.dispatchservice.feignClient;

import intelligent_taxi.dispatchservice.dto.taxi.TaxiResponse;
import intelligent_taxi.dispatchservice.feignClient.constant.TaxiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static intelligent_taxi.dispatchservice.feignClient.constant.TaxiUrl.*;

@FeignClient(name = BASE)
public interface TaxiClient {

    @GetMapping(TAXI_INFO_BY_USERNAME)
    TaxiResponse getTaxiInfoByUsername(@PathVariable(TaxiParam.USERNAME) String username);
}
