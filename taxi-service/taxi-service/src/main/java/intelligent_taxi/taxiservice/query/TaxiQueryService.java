package intelligent_taxi.taxiservice.query;

import intelligent_taxi.taxiservice.repository.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaxiQueryService {

    private final TaxiRepository taxiRepository;


}
