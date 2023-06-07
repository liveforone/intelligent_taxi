package intelligent_taxi.dispatchservice.command;

import intelligent_taxi.dispatchservice.clientWrapper.TaxiClientWrapper;
import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import intelligent_taxi.dispatchservice.dto.dispatch.RequestDispatch;
import intelligent_taxi.dispatchservice.dto.taxi.TaxiResponse;
import intelligent_taxi.dispatchservice.repository.DispatchRepository;
import intelligent_taxi.dispatchservice.validator.ServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DispatchCommandService {

    private final DispatchRepository dispatchRepository;
    private final TaxiClientWrapper taxiClientWrapper;
    private final DispatchProducer dispatchProducer;
    private final ServiceValidator serviceValidator;

    public Long createDispatch(DispatchRequest requestDto, String username) {
        Dispatch dispatch = Dispatch.create(requestDto, username);
        return dispatchRepository.save(dispatch).getId();
    }

    public void removeDispatch(Long id, String username) {
        Dispatch dispatch = serviceValidator.validateRemoveDispatch(id, username);
        dispatchRepository.delete(dispatch);
    }

    public void rollbackDispatch(Long id) {
        Dispatch dispatch = dispatchRepository.findOneById(id);
        dispatchRepository.delete(dispatch);
    }

    public void dispatch(RequestDispatch requestDto, String username) {
        Dispatch dispatch = serviceValidator.validateDispatch(requestDto);
        dispatch.finishDispatch();

        TaxiResponse taxi = taxiClientWrapper.getTaxiInfoByUsername(username);
        dispatchProducer.requestOrder(dispatch.getId(), taxi.getId(), dispatch.getPrice());
        dispatchProducer.requestCalculate(dispatch.getId(), taxi.getId(), dispatch.getDistance(), dispatch.getPrice());
    }
}
