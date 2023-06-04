package intelligent_taxi.taxiservice.command;

import intelligent_taxi.taxiservice.domain.Taxi;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.dto.UpdateRegion;
import intelligent_taxi.taxiservice.repository.TaxiRepository;
import intelligent_taxi.taxiservice.validator.ServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaxiCommandService {

    private final TaxiRepository taxiRepository;
    private final ServiceValidator serviceValidator;

    public Long createTaxi(TaxiRequest requestDto, String username) {
        Taxi taxi = Taxi.create(requestDto, username);
        return taxiRepository.save(taxi).getId();
    }

    public void updateRegion(UpdateRegion requestDto, String username, Long id) {
        serviceValidator.validateUpdateRegion(username, id);

        Taxi taxi = taxiRepository.findOneById(id);
        taxi.updateRegion(requestDto.getRegion());
    }
}
