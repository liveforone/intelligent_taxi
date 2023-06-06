package intelligent_taxi.dispatchservice.repository;

import intelligent_taxi.dispatchservice.domain.Dispatch;

public interface DispatchCustomRepository {
    Dispatch findOneByUsername(String username);
    Dispatch findOneById(Long id);
}
