package intelligent_taxi.taxiservice.repository;

import intelligent_taxi.taxiservice.domain.Taxi;

import java.util.Optional;

public interface TaxiCustomRepository {

    Optional<Taxi> findOneById(Long id);
    Optional<Taxi> findOneByUsername(String username);
}
