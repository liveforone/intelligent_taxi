package intelligent_taxi.dispatchservice.query;

import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchResponse;
import intelligent_taxi.dispatchservice.exception.DispatchCustomException;
import intelligent_taxi.dispatchservice.query.util.DispatchMapper;
import intelligent_taxi.dispatchservice.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DispatchQueryService {

    private final DispatchRepository dispatchRepository;

    public DispatchResponse getCurrentDispatchByUsername(String username) {
        return DispatchMapper.entityToDto(dispatchRepository.findCurrentOneByUsername(username)
                .orElseThrow(() -> new DispatchCustomException(ResponseMessage.DISPATCH_IS_NULL)));
    }

    public DispatchResponse getDispatchById(Long id) {
        return DispatchMapper.entityToDto(dispatchRepository.findOneById(id)
                .orElseThrow(() -> new DispatchCustomException(ResponseMessage.DISPATCH_IS_NULL)));
    }
}
