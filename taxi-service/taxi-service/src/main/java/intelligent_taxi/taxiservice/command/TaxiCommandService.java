package intelligent_taxi.taxiservice.command;

import intelligent_taxi.taxiservice.dto.TaxiRequest;
import intelligent_taxi.taxiservice.repository.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaxiCommandService {

    private final TaxiRepository taxiRepository;


}
