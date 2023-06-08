package intelligent_taxi.dispatchservice.repository;

import intelligent_taxi.dispatchservice.domain.Dispatch;

import java.util.Optional;

public interface DispatchCustomRepository {
    Optional<Dispatch> findOneById(Long id);
    Optional<Dispatch> findCurrentOneByUsername(String username);
    Optional<Dispatch> findOneWithinDistance(Double latitude, Double longitude);
}
