package intelligent_taxi.taxiservice.repository;

import intelligent_taxi.taxiservice.domain.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi, Long>, TaxiCustomRepository {
}
