package intelligent_taxi.taxiservice.command;

import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.domain.Taxi;
import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.dto.UpdateGradeRequest;
import intelligent_taxi.taxiservice.dto.UpdateRegionRequest;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;
import intelligent_taxi.taxiservice.repository.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaxiCommandService {

    private final TaxiRepository taxiRepository;

    public Long createTaxi(TaxiRequest requestDto, String username) {
        Taxi taxi = Taxi.create(requestDto, username);
        return taxiRepository.save(taxi).getId();
    }

    public void updateRegion(UpdateRegionRequest requestDto, String username, Long id) {
        Taxi taxi = taxiRepository.findOneById(id)
                .orElseThrow(() -> new TaxiCustomException(ResponseMessage.TAXI_IS_NULL));
        taxi.updateRegion(requestDto.getRegion(), username);
    }

    public void updateGrade(UpdateGradeRequest requestDto, String username, Long id) {
        Taxi taxi = taxiRepository.findOneById(id)
                .orElseThrow(() -> new TaxiCustomException(ResponseMessage.TAXI_IS_NULL));
        taxi.updateGrade(requestDto.getTaxiGrade(), username);
    }

    public void deleteOneByUsername(String username) {
        Taxi taxi = taxiRepository.findOneByUsername(username)
                .orElseThrow(() -> new TaxiCustomException(ResponseMessage.TAXI_IS_NULL));
        taxiRepository.delete(taxi);
    }

    public void deleteOneById(Long id, String username) {
        Taxi taxi = taxiRepository.findOneById(id)
                .orElseThrow(() -> new TaxiCustomException(ResponseMessage.TAXI_IS_NULL));

        if (!taxi.getUsername().equals(username)) {
            throw new TaxiCustomException(ResponseMessage.NOT_MATCH_USERNAME);
        }

        taxiRepository.delete(taxi);
    }
}
