package intelligent_taxi.dispatchservice.validator;

import intelligent_taxi.dispatchservice.authentication.Role;
import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.dispatchservice.exception.DispatchCustomException;
import org.springframework.stereotype.Component;

@Component
public class ControllerValidator {
    public void validateAuthIsMember(String auth) {
        if (!auth.equals(Role.MEMBER.getAuth())) {
            throw new DispatchCustomException(ResponseMessage.AUTH_IS_NOT_MEMBER);
        }
    }
}
