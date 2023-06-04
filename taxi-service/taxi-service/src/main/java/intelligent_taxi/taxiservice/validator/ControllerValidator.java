package intelligent_taxi.taxiservice.validator;

import intelligent_taxi.taxiservice.authentication.Role;
import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;
import org.springframework.stereotype.Component;

@Component
public class ControllerValidator {

    public void validateAuth(String auth) {
        if (!auth.equals(Role.TAXI.getAuth())) {
            throw new TaxiCustomException(ResponseMessage.NOT_TAXI_MEMBER);
        }
    }
}
