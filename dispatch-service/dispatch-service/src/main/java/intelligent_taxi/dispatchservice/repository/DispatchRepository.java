package intelligent_taxi.dispatchservice.repository;

import intelligent_taxi.dispatchservice.domain.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchRepository extends JpaRepository<Dispatch,Long>, DispatchCustomRepository {
}
