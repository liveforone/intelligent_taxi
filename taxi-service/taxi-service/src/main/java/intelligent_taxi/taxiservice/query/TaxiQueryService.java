package intelligent_taxi.taxiservice.query;

import intelligent_taxi.taxiservice.dto.TaxiResponse;
import intelligent_taxi.taxiservice.query.util.TaxiMapper;
import intelligent_taxi.taxiservice.repository.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaxiQueryService {

    private final TaxiRepository taxiRepository;

    public TaxiResponse getTaxiByUsername(String username) {
        return TaxiMapper.entityToDto(taxiRepository.findOneByUsername(username));
    }

    public TaxiResponse getTaxiById(Long id) {
        return TaxiMapper.entityToDto(taxiRepository.findOneById(id));
    }
}
