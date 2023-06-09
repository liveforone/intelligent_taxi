package intelligent_taxi.dispatchservice.validator;

import intelligent_taxi.dispatchservice.authentication.Role;
import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.dispatchservice.exception.BindingCustomException;
import intelligent_taxi.dispatchservice.exception.DispatchCustomException;
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

    public void validateAuthIsMember(String auth) {
        if (!auth.equals(Role.MEMBER.getAuth())) {
            throw new DispatchCustomException(ResponseMessage.AUTH_IS_NOT_MEMBER);
        }
    }

    public void validateAuthIsTaxi(String auth) {
        if (!auth.equals(Role.TAXI.getAuth())) {
            throw new DispatchCustomException(ResponseMessage.AUTH_IS_NOT_TAXI);
        }
    }
}
