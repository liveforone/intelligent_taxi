package intelligent_taxi.dispatchservice.command;

import intelligent_taxi.dispatchservice.clientWrapper.MemberClientWrapper;
import intelligent_taxi.dispatchservice.clientWrapper.PayClientWrapper;
import intelligent_taxi.dispatchservice.clientWrapper.TaxiClientWrapper;
import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.domain.DispatchState;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import intelligent_taxi.dispatchservice.dto.dispatch.RequestDispatch;
import intelligent_taxi.dispatchservice.dto.taxi.TaxiResponse;
import intelligent_taxi.dispatchservice.exception.DispatchCustomException;
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
    private final PayClientWrapper payClientWrapper;
    private final MemberClientWrapper memberClientWrapper;
    private final DispatchProducer dispatchProducer;
    private final ServiceValidator serviceValidator;

    public Long createDispatch(DispatchRequest requestDto, String username) {
        Dispatch dispatch = Dispatch.create(requestDto, username);

        String bankbookNum = memberClientWrapper.getBankbookNum(username);
        boolean checkBalance = payClientWrapper.checkBalance(bankbookNum, dispatch.getPrice());
        serviceValidator.validateCheckBalance(checkBalance);

        return dispatchRepository.save(dispatch).getId();
    }

    public void removeDispatch(Long id, String username) {
        Dispatch dispatch = dispatchRepository.findOneById(id)
                .orElseThrow(() -> new DispatchCustomException(ResponseMessage.DISPATCH_IS_NULL));

        if (!username.equals(dispatch.getUsername())) {
            throw new DispatchCustomException(ResponseMessage.NOT_MATCH_USERNAME);
        }

        if (dispatch.getDispatchState() == DispatchState.FINISH) {
            throw new DispatchCustomException(ResponseMessage.DISPATCH_IS_ALREADY_FINISH);
        }

        dispatchRepository.delete(dispatch);
    }

    public void rollbackDispatch(Long id) {
        Dispatch dispatch = dispatchRepository.findOneById(id)
                .orElseThrow(() -> new DispatchCustomException(ResponseMessage.DISPATCH_IS_NULL));
        dispatchRepository.delete(dispatch);
    }

    public void dispatch(RequestDispatch requestDto, String username) {
        Dispatch dispatch = dispatchRepository.findOneWithinDistance(
                requestDto.getPresentLatitude(),
                requestDto.getPresentLongitude()
        ).orElseThrow(() -> new DispatchCustomException(ResponseMessage.DISPATCH_IS_NULL));
        dispatch.finishDispatch();

        TaxiResponse taxi = taxiClientWrapper.getTaxiInfoByUsername(username);
        dispatchProducer.requestOrder(dispatch.getId(), taxi.getId(), dispatch.getPrice());
        dispatchProducer.requestCalculate(dispatch.getId(), taxi.getId(), dispatch.getDistance(), dispatch.getPrice());
    }
}
