package intelligent_taxi.dispatchservice.feignClient;

import intelligent_taxi.dispatchservice.feignClient.constant.MemberParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static intelligent_taxi.dispatchservice.feignClient.constant.MemberUrl.*;

@FeignClient(name = BASE)
public interface MemberClient {

    @GetMapping(MY_BANKBOOK_NUM)
    String getBankbookNum(@PathVariable(MemberParam.USERNAME) String username);
}
