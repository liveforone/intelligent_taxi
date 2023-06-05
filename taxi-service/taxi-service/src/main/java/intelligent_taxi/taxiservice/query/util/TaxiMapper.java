package intelligent_taxi.taxiservice.query.util;

import intelligent_taxi.taxiservice.domain.Taxi;
import intelligent_taxi.taxiservice.dto.TaxiResponse;
import intelligent_taxi.taxiservice.utility.CommonUtils;

public class TaxiMapper {
    public static TaxiResponse entityToDto(Taxi taxi) {
        if (CommonUtils.isNull(taxi)) {
            return new TaxiResponse();
        }

        return TaxiResponse.builder()
                .id(taxi.getId())
                .taxiGrade(taxi.getTaxiGrade())
                .licenseNum(taxi.getLicenseNum())
                .phoneNum(taxi.getPhoneNum())
                .region(taxi.getRegion().getRegion())
                .build();
    }
}
