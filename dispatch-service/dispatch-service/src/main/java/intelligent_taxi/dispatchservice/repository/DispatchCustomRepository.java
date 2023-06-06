package intelligent_taxi.dispatchservice.repository;

import intelligent_taxi.dispatchservice.domain.Dispatch;

public interface DispatchCustomRepository {
    Dispatch findOneById(Long id);
    Dispatch findCurrentOneByUsername(String username);
}
