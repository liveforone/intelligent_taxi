package intelligent_taxi.taxiservice.validator;

import intelligent_taxi.taxiservice.authentication.Role;
import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.exception.BindingCustomException;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Component
public class ControllerValidator {

    public void validateBinding(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            throw new BindingCustomException(errorMessage);
        }
    }

    public void validateAuth(String auth) {
        if (!auth.equals(Role.TAXI.getAuth())) {
            throw new TaxiCustomException(ResponseMessage.NOT_TAXI_MEMBER);
        }
    }
}
