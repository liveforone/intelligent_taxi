package intelligent_taxi.taxiservice.command;

import intelligent_taxi.taxiservice.domain.Taxi;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.dto.UpdateGradeRequest;
import intelligent_taxi.taxiservice.dto.UpdateRegionRequest;
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

    public void updateRegion(UpdateRegionRequest requestDto, String username, Long id) {
        Taxi taxi = serviceValidator.validateTaxiAndUsername(username, id);
        taxi.updateRegion(requestDto.getRegion());
    }

    public void updateGrade(UpdateGradeRequest requestDto, String username, Long id) {
        Taxi taxi = serviceValidator.validateTaxiAndUsername(username, id);
        taxi.updateGrade(requestDto.getTaxiGrade());
    }

    public void deleteOneByUsername(String username) {
        Taxi taxi = serviceValidator.validateDeleteOneByUsername(username);
        taxiRepository.delete(taxi);
    }

    public void deleteOneById(Long id, String username) {
        Taxi taxi = serviceValidator.validateTaxiAndUsername(username, id);
        taxiRepository.delete(taxi);
    }
}
