package intelligent_taxi.taxiservice.query.util;

import intelligent_taxi.taxiservice.domain.Taxi;
import intelligent_taxi.taxiservice.dto.TaxiResponse;

public class TaxiMapper {
    public static TaxiResponse entityToDto(Taxi taxi) {
        return TaxiResponse.builder()
                .id(taxi.getId())
                .taxiGrade(taxi.getTaxiGrade())
                .licenseNum(taxi.getLicenseNum())
                .phoneNum(taxi.getPhoneNum())
                .region(taxi.getRegion().getRegion())
                .build();
    }
}
