package intelligent_taxi.dispatchservice.command;

import intelligent_taxi.dispatchservice.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DispatchCommandService {

    private final DispatchRepository dispatchRepository;


}
