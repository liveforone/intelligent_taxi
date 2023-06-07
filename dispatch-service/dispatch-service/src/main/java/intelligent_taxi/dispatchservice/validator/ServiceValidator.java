package intelligent_taxi.dispatchservice.validator;

import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.exception.DispatchCustomException;
import intelligent_taxi.dispatchservice.repository.DispatchRepository;
import intelligent_taxi.dispatchservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceValidator {

    private final DispatchRepository dispatchRepository;

    public Dispatch validateUsername(Long id, String username) {
        Dispatch dispatch = dispatchRepository.findOneById(id);

        if (CommonUtils.isNull(dispatch)) {
            throw new DispatchCustomException(ResponseMessage.DISPATCH_IS_NULL);
        }

        if (!username.equals(dispatch.getUsername())) {
            throw new DispatchCustomException(ResponseMessage.NOT_MATCH_USERNAME);
        }

        return dispatch;
    }
}
