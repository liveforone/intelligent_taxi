package intelligent_taxi.taxiservice.validator;

import intelligent_taxi.taxiservice.repository.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceValidator {

    private final TaxiRepository taxiRepository;
}
