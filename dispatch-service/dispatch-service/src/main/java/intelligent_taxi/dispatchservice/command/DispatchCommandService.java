package intelligent_taxi.dispatchservice.command;

import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import intelligent_taxi.dispatchservice.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DispatchCommandService {

    private final DispatchRepository dispatchRepository;

    public Long createDispatch(DispatchRequest requestDto, String username) {
        Dispatch dispatch = Dispatch.create(requestDto, username);
        return dispatchRepository.save(dispatch).getId();
    }
}
