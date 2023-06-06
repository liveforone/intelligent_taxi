package intelligent_taxi.dispatchservice.query.util;

import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchResponse;
import intelligent_taxi.dispatchservice.utility.CommonUtils;

public class DispatchMapper {

    public static DispatchResponse entityToDto(Dispatch dispatch) {
        if (CommonUtils.isNull(dispatch)) {
            return new DispatchResponse();
        }
        return DispatchResponse.builder()
                .id(dispatch.getId())
                .presentLatitude(dispatch.getPresentLatitude())
                .presentLongitude(dispatch.getPresentLongitude())
                .destinationLatitude(dispatch.getDestinationLatitude())
                .destinationLongitude(dispatch.getDestinationLongitude())
                .price(dispatch.getPrice())
                .dispatchState(dispatch.getDispatchState())
                .createdDate(dispatch.getCreatedDate())
                .build();
    }
}
