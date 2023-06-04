package intelligent_taxi.taxiservice.validator;

import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.domain.Taxi;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;
import intelligent_taxi.taxiservice.repository.TaxiRepository;
import intelligent_taxi.taxiservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceValidator {

    private final TaxiRepository taxiRepository;

    public void validateUsernameAndId(String username, Long id) {
        Taxi taxi = taxiRepository.findOneById(id);

        if (CommonUtils.isNull(taxi)) {
            throw new TaxiCustomException(ResponseMessage.TAXI_IS_NULL);
        }

        if (!taxi.getUsername().equals(username)) {
            throw new TaxiCustomException(ResponseMessage.NOT_MATCH_USERNAME);
        }
    }
}
