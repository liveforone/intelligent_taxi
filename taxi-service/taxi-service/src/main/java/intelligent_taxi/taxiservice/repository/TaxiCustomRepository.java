package intelligent_taxi.taxiservice.repository;

import intelligent_taxi.taxiservice.domain.Taxi;

public interface TaxiCustomRepository {

    Taxi findOneById(Long id);
    Taxi findOneByUsername(String username);
}
