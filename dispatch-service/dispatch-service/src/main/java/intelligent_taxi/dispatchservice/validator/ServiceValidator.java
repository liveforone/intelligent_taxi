package intelligent_taxi.dispatchservice.validator;

import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.dispatchservice.exception.DispatchCustomException;
import org.springframework.stereotype.Component;

@Component
public class ServiceValidator {

    public void validateCheckBalance(Boolean checkBalance) {
        if (!checkBalance) {
            throw new DispatchCustomException(ResponseMessage.CHECK_BALANCE_FAIL);
        }
    }
}
